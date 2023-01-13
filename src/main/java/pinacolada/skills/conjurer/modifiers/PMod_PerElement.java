package pinacolada.skills.conjurer.modifiers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import extendedui.EUIRM;
import extendedui.EUIUtils;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.cards.base.PCLUseInfo;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PSkill;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.fields.PField_Affinity;
import pinacolada.ui.combat.ConjurerReactionMeter;

import java.util.List;

public class PMod_PerElement extends PMod<PField_Affinity>
{

    public static final PSkillData<PField_Affinity> DATA = register(PMod_PerElement.class, PField_Affinity.class, ConjurerEnum.Cards.THE_CONJURER);

    public PMod_PerElement(PSkillSaveData content)
    {
        super(DATA, content);
    }

    public PMod_PerElement()
    {
        super(DATA);
    }

    public PMod_PerElement(int amount, PCLAffinity... affinities)
    {
        this(PCLCardTarget.AllEnemy, amount, affinities);
    }

    public PMod_PerElement(PCLCardTarget target, int amount, PCLAffinity... affinities)
    {
        super(DATA, target, amount);
        fields.setAffinity(affinities);
    }

    @Override
    public int getModifiedAmount(PSkill be, PCLUseInfo info)
    {
        List<AbstractCreature> targetList = getTargetList(info);
        return be.baseAmount *
                EUIUtils.sumInt(targetList, t -> t.powers != null ? EUIUtils.sumInt(t.powers, po -> isPowerElemental(po.ID) ? po.amount : 0) : 0) / Math.max(1, this.amount);
    }

    @Override
    public String getSampleText()
    {
        return TEXT.conditions.per("X", ConjurerResources.conjurer.tooltips.elementalDebuff);
    }

    @Override
    public String getSubText()
    {
        String baseString = fields.affinities.isEmpty() ? plural(ConjurerResources.conjurer.tooltips.elementalDebuff) : PCLElementHelper.getPowerAndString(fields.affinities);
        if (amount > 1)
        {
            baseString = EUIRM.strings.numNoun(getAmountRawString(), baseString);
        }
        switch (target)
        {
            case All:
            case Any:
                return TEXT.subjects.onAnyCharacter(baseString);
            case AllEnemy:
                return TEXT.subjects.onAnyEnemy(baseString);
            case Single:
                return TEXT.subjects.onTheEnemy(baseString);
            case Self:
                return TEXT.subjects.onYou(baseString);
            default:
                return baseString;
        }
    }

    protected boolean isPowerElemental(String id)
    {
        return fields.affinities.isEmpty() ? ConjurerReactionMeter.meter.isPowerElemental(id) : EUIUtils.any(fields.affinities, a -> ConjurerReactionMeter.meter.isPowerElemental(id, a));
    }
}

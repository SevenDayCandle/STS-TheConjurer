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
import pinacolada.ui.combat.ConjurerReactionMeter;

import java.util.List;

import static pinacolada.skills.PSkill.PCLEffectType.Power;

public class PMod_PerElement extends PMod
{

    public static final PSkillData DATA = register(PMod_PerElement.class, Power, ConjurerEnum.Cards.THE_CONJURER);

    public PMod_PerElement(PSkillSaveData content)
    {
        super(content);
    }

    public PMod_PerElement()
    {
        super(DATA);
    }

    public PMod_PerElement(int amount, PCLAffinity... powerHelpers)
    {
        super(DATA, PCLCardTarget.AllEnemy, amount, powerHelpers);
    }

    public PMod_PerElement(PCLCardTarget target, int amount, PCLAffinity... powerHelpers)
    {
        super(DATA, target, amount, powerHelpers);
    }

    public PMod_PerElement(int amount, List<PCLAffinity> powerHelpers)
    {
        super(DATA, PCLCardTarget.AllEnemy, amount, powerHelpers.toArray(new PCLAffinity[]{}));
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
        String baseString = affinities.isEmpty() ? plural(ConjurerResources.conjurer.tooltips.elementalDebuff) : PCLElementHelper.getPowerAndString(affinities);
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
        return affinities.isEmpty() ? ConjurerReactionMeter.meter.isPowerElemental(id) : EUIUtils.any(affinities, a -> ConjurerReactionMeter.meter.isPowerElemental(id, a));
    }
}

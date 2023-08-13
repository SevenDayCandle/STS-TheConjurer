package pinacolada.skills.conjurer.modifiers;

import extendedui.EUIRM;
import extendedui.EUIUtils;
import pinacolada.annotations.VisibleSkill;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.fields.PField_Affinity;
import pinacolada.skills.skills.base.modifiers.PMod_Per;

@VisibleSkill
public class PMod_PerElement extends PMod_Per<PField_Affinity> {

    public static final PSkillData<PField_Affinity> DATA = register(PMod_PerElement.class, PField_Affinity.class, ConjurerEnum.Cards.THE_CONJURER);

    public PMod_PerElement(PSkillSaveData content) {
        super(DATA, content);
    }

    public PMod_PerElement() {
        super(DATA);
    }

    public PMod_PerElement(int amount, PCLAffinity... affinities) {
        this(PCLCardTarget.AllEnemy, amount, affinities);
    }

    public PMod_PerElement(PCLCardTarget target, int amount, PCLAffinity... affinities) {
        super(DATA, target, amount);
        fields.setAffinity(affinities);
    }

    @Override
    public String getSubSampleText() {
        return ConjurerResources.conjurer.tooltips.elementalDebuff.title;
    }

    @Override
    public int getMultiplier(PCLUseInfo info, boolean isUsing) {
        return EUIUtils.sumInt(getTargetList(info), t -> t.powers != null ? EUIUtils.sumInt(t.powers, po -> isPowerElemental(po.ID) ? po.amount : 0) : 0) / Math.max(1, this.amount);
    }

    @Override
    public String getSubText(PCLCardTarget perpsective) {
        String baseString = fields.affinities.isEmpty() ? plural(ConjurerResources.conjurer.tooltips.elementalDebuff) : PCLElementHelper.getPowerAndString(fields.affinities);
        if (amount > 1) {
            baseString = EUIRM.strings.numNoun(getAmountRawString(), baseString);
        }
        switch (target) {
            case All:
            case Any:
                return TEXT.subjects_onAnyCharacter(baseString);
            case AllEnemy:
                return TEXT.subjects_onAnyEnemy(baseString);
            case Single:
                return TEXT.subjects_onTheEnemy(baseString);
            case Self:
                return TEXT.subjects_onYou(baseString);
            default:
                return baseString;
        }
    }

    protected boolean isPowerElemental(String id) {
        return fields.affinities.isEmpty() ? ConjurerReactionMeter.meter.isPowerElemental(id) : EUIUtils.any(fields.affinities, a -> ConjurerReactionMeter.meter.isPowerElemental(id, a));
    }
}

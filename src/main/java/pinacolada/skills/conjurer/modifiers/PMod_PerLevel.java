package pinacolada.skills.conjurer.modifiers;

import extendedui.EUIRM;
import extendedui.EUIUtils;
import pinacolada.annotations.VisibleSkill;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.resources.conjurer.ConjurerEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.fields.PField_Affinity;
import pinacolada.skills.skills.base.modifiers.PMod_Per;

@VisibleSkill
public class PMod_PerLevel extends PMod_Per<PField_Affinity> {
    public static final PSkillData<PField_Affinity> DATA = register(PMod_PerLevel.class, PField_Affinity.class, ConjurerEnum.Cards.THE_CONJURER)
            .noTarget();

    public PMod_PerLevel(PSkillSaveData content) {
        super(DATA, content);
    }

    public PMod_PerLevel() {
        super(DATA);
    }

    public PMod_PerLevel(int amount, PCLAffinity... affinities) {
        super(DATA, amount);
        fields.setAffinity(affinities);
    }

    @Override
    public int getMultiplier(PCLUseInfo info, boolean isUsing) {
        return EUIUtils.sumInt(fields.affinities, ConjurerReactionMeter.meter::getLevel);
    }

    @Override
    public String getSubSampleText() {
        return ConjurerResources.conjurer.tooltips.reaction.title;
    }

    @Override
    public String getSubText(PCLCardTarget perspective, Object requestor) {
        return EUIRM.strings.adjNoun(fields.getAffinityAndOrString(), ConjurerResources.conjurer.tooltips.reaction.title);
    }
}

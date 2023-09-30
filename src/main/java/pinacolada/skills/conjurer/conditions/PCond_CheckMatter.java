package pinacolada.skills.conjurer.conditions;

import extendedui.EUIRM;
import pinacolada.annotations.VisibleSkill;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.fields.PField_Not;
import pinacolada.skills.skills.PPassiveCond;

import static pinacolada.resources.conjurer.ConjurerEnum.Cards.THE_CONJURER;

@VisibleSkill
public class PCond_CheckMatter extends PPassiveCond<PField_Not> {
    public static final PSkillData<PField_Not> DATA = register(PCond_CheckMatter.class, PField_Not.class)
            .setColors(THE_CONJURER)
            .noTarget();

    public PCond_CheckMatter(PSkillSaveData content) {
        super(DATA, content);
    }

    public PCond_CheckMatter() {
        super(DATA, PCLCardTarget.None, 1);
    }

    public PCond_CheckMatter(int amount) {
        super(DATA, PCLCardTarget.None, amount);
    }

    @Override
    public boolean checkCondition(PCLUseInfo info, boolean isUsing, PSkill<?> triggerSource) {
        return fields.doesValueMatchThreshold(ConjurerReactionMeter.meter.getMatter());
    }

    @Override
    public String getSampleText(PSkill<?> callingSkill, PSkill<?> parentSkill) {
        return EUIRM.strings.numNoun(TEXT.subjects_x, ConjurerResources.conjurer.tooltips.matter.title);
    }

    @Override
    public String getSubText(PCLCardTarget perpsective) {
        return getTargetHasStringPerspective(perpsective, fields.getThresholdRawString(ConjurerResources.conjurer.tooltips.matter.title));
    }
}

package pinacolada.skills.conjurer.conditions;

import extendedui.EUIRM;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.fields.PField_Not;
import pinacolada.skills.skills.PPassiveCond;
import pinacolada.ui.combat.ConjurerReactionMeter;

import static pinacolada.resources.conjurer.ConjurerEnum.Cards.THE_CONJURER;

public class PCond_CheckReaction extends PPassiveCond<PField_Not> {
    public static final PSkillData<PField_Not> DATA = register(PCond_CheckReaction.class, PField_Not.class)
            .setColors(THE_CONJURER)
            .selfTarget();

    public PCond_CheckReaction(PSkillSaveData content) {
        super(DATA, content);
    }

    public PCond_CheckReaction() {
        super(DATA, PCLCardTarget.None, 1);
    }

    public PCond_CheckReaction(int amount) {
        super(DATA, PCLCardTarget.None, amount);
    }

    @Override
    public boolean checkCondition(PCLUseInfo info, boolean isUsing, PSkill<?> triggerSource) {
        return fields.doesValueMatchThreshold(ConjurerReactionMeter.meter.getReactionCount());
    }

    @Override
    public String getSampleText(PSkill<?> callingSkill) {
        return EUIRM.strings.numNoun(TEXT.subjects_x, ConjurerResources.conjurer.tooltips.reaction.title);
    }

    @Override
    public String getSubText() {
        return getTargetHasString(fields.getThresholdRawString(ConjurerResources.conjurer.tooltips.reaction.title));
    }

    @Override
    public String wrapAmount(int input) {
        return fields.getThresholdValString(input);
    }
}

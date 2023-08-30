package pinacolada.skills.conjurer.modifiers;

import extendedui.EUIRM;
import extendedui.EUIUtils;
import pinacolada.annotations.VisibleSkill;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.AffinityReactions;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.resources.conjurer.ConjurerEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.fields.PField_Affinity;
import pinacolada.skills.skills.base.modifiers.PMod_BonusOn;

@VisibleSkill
public class PMod_BonusOnReact extends PMod_BonusOn<PField_Affinity> {
    public static final PSkillData<PField_Affinity> DATA = register(PMod_BonusOnReact.class, PField_Affinity.class, ConjurerEnum.Cards.THE_CONJURER)
            .selfTarget();

    public PMod_BonusOnReact(PSkillSaveData content) {
        super(DATA, content);
    }

    public PMod_BonusOnReact() {
        this(0);
    }

    public PMod_BonusOnReact(int amount) {
        super(DATA, amount);
    }

    @Override
    public String getConditionText(PCLCardTarget perpsective) {
        return EUIRM.strings.generic2(getAmountRawString(), TEXT.cond_ifTargetHas(TEXT.subjects_this, PCLCardTarget.Single.ordinal(), fields.not ? TEXT.cond_not(getSubText(perpsective)) : getSubText(perpsective)));
    }

    @Override
    public String getSampleText(PSkill<?> callingSkill, PSkill<?> parentSkill) {
        return TEXT.cond_bonusIf(TEXT.subjects_x, ConjurerResources.conjurer.tooltips.reaction.title);
    }

    @Override
    public String getSubText(PCLCardTarget perpsective) {
        return fields.affinities.isEmpty() ? ConjurerResources.conjurer.tooltips.reaction.past() : TEXT.subjects_withX(ConjurerResources.conjurer.tooltips.reaction.past(), fields.getAffinityOrString());
    }

    @Override
    public boolean meetsCondition(PCLUseInfo info) {
        AffinityReactions reactions = info.getAux(ConjurerReactionMeter.meter, AffinityReactions.class);
        if (reactions == null) {
            return false;
        }
        return fields.affinities.isEmpty() ? reactions.hasReaction() : EUIUtils.all(fields.affinities, reactions::hasReaction);
    }
}

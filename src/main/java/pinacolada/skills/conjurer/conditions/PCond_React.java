package pinacolada.skills.conjurer.conditions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import extendedui.EUIUtils;
import pinacolada.annotations.VisibleSkill;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.AffinityReactions;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.interfaces.subscribers.OnElementReactSubscriber;
import pinacolada.resources.conjurer.ConjurerEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.fields.PField_Affinity;
import pinacolada.skills.skills.PPassiveCond;

@VisibleSkill
public class PCond_React extends PPassiveCond<PField_Affinity> implements OnElementReactSubscriber {
    public static final PSkillData<PField_Affinity> DATA = register(PCond_React.class, PField_Affinity.class, 1, 1, ConjurerEnum.Cards.THE_CONJURER)
            .noTarget();

    public PCond_React() {
        super(DATA, PCLCardTarget.None, 0);
    }

    public PCond_React(PCLAffinity... affinities) {
        super(DATA, PCLCardTarget.None, 0);
        fields.setAffinity(affinities);
    }

    public PCond_React(PSkillSaveData content) {
        super(DATA, content);
    }

    @Override
    public boolean checkCondition(PCLUseInfo info, boolean isUsing, PSkill<?> triggerSource) {
        AffinityReactions reactions = info.getAux(ConjurerReactionMeter.meter, AffinityReactions.class);
        if (reactions == null) {
            return false;
        }
        return fields.not ^ (fields.affinities.isEmpty() ? reactions.hasReaction() : EUIUtils.all(fields.affinities, reactions::hasReaction));
    }

    @Override
    public String getSampleText(PSkill<?> callingSkill, PSkill<?> parentSkill) {
        return TEXT.cond_onGeneric(ConjurerResources.conjurer.tooltips.reaction.title);
    }

    @Override
    public String getSubText(PCLCardTarget perpsective, Object requestor) {
        if (isWhenClause()) {
            return TEXT.cond_aObject(TEXT.subjects_card, fields.affinities.isEmpty() ? ConjurerResources.conjurer.tooltips.reaction.present() : TEXT.subjects_withX(ConjurerResources.conjurer.tooltips.reaction.past(), fields.getAffinityOrString()));
        }
        String base = fields.affinities.isEmpty() ? ConjurerResources.conjurer.tooltips.reaction.past() : TEXT.subjects_withX(ConjurerResources.conjurer.tooltips.reaction.past(), fields.getAffinityOrString());
        return TEXT.cond_ifTargetHas(TEXT.subjects_this, PCLCardTarget.Single.ordinal(), fields.not ? TEXT.cond_not(base) : base);
    }

    @Override
    public void onElementReact(PCLUseInfo info, AffinityReactions reactions, AbstractCreature abstractCreature) {
        if (fields.affinities.isEmpty() ? reactions.hasReaction() : EUIUtils.all(fields.affinities, reactions::hasReaction)) {
            useFromTrigger(generateInfo(abstractCreature));
        }
    }
}

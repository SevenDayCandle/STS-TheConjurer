package pinacolada.skills.conjurer.conditions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import extendedui.EUIUtils;
import pinacolada.annotations.VisibleSkill;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.interfaces.subscribers.OnElementReactSubscriber;
import pinacolada.misc.AffinityReactions;
import pinacolada.misc.ConjurerUseInfo;
import pinacolada.resources.conjurer.ConjurerEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.fields.PField_Affinity;
import pinacolada.skills.skills.PPassiveCond;
import pinacolada.skills.skills.PTrigger;

@VisibleSkill
public class PCond_React extends PPassiveCond<PField_Affinity> implements OnElementReactSubscriber {
    public static final PSkillData<PField_Affinity> DATA = register(PCond_React.class, PField_Affinity.class, 1, 1)
            .setColors(ConjurerEnum.Cards.THE_CONJURER)
            .selfTarget();

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
        ConjurerUseInfo cInfo = EUIUtils.safeCast(info, ConjurerUseInfo.class);
        if (cInfo == null) {
            return false;
        }
        return fields.not ^ (fields.affinities.isEmpty() ? cInfo.reactions.hasReaction() : EUIUtils.all(fields.affinities, cInfo.reactions::hasReaction));
    }

    @Override
    public String getSubText(PCLCardTarget perpsective) {
        if (hasParentType(PTrigger.class)) {
            return TEXT.cond_whenAObject(TEXT.subjects_card, fields.affinities.isEmpty() ? ConjurerResources.conjurer.tooltips.reaction.present() : TEXT.subjects_withX(ConjurerResources.conjurer.tooltips.reaction.past(), fields.getAffinityOrString()));
        }
        String base = fields.affinities.isEmpty() ? ConjurerResources.conjurer.tooltips.reaction.past() : TEXT.subjects_withX(ConjurerResources.conjurer.tooltips.reaction.past(), fields.getAffinityOrString());
        return TEXT.cond_ifTargetHas(TEXT.subjects_this, PCLCardTarget.Single.ordinal(), fields.not ? TEXT.cond_not(base) : base);
    }

    @Override
    public void onElementReact(AffinityReactions reactions, AbstractCreature abstractCreature) {
        if (fields.affinities.isEmpty() ? reactions.hasReaction() : EUIUtils.all(fields.affinities, reactions::hasReaction)) {
            useFromTrigger(generateInfo(abstractCreature));
        }
    }
}

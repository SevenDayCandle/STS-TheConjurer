package pinacolada.skills.conjurer.conditions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import extendedui.EUIRM;
import extendedui.EUIUtils;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.interfaces.subscribers.OnElementReactSubscriber;
import pinacolada.misc.AffinityReactions;
import pinacolada.misc.ConjurerUseInfo;
import pinacolada.misc.PCLUseInfo;
import pinacolada.resources.conjurer.ConjurerEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.fields.PField_Affinity;
import pinacolada.skills.skills.PPassiveCond;
import pinacolada.skills.skills.PTrigger;

public class PCond_Combust extends PPassiveCond<PField_Affinity> implements OnElementReactSubscriber
{
    public static final PSkillData<PField_Affinity> DATA = register(PCond_Combust.class, PField_Affinity.class, 1, 1)
            .setColors(ConjurerEnum.Cards.THE_CONJURER)
            .selfTarget();

    public PCond_Combust()
    {
        super(DATA, PCLCardTarget.None, 0);
    }

    public PCond_Combust(PCLAffinity... affinities)
    {
        super(DATA, PCLCardTarget.None, 0);
        fields.setAffinity(affinities);
    }

    public PCond_Combust(PSkillSaveData content)
    {
        super(DATA, content);
    }

    @Override
    public boolean checkCondition(PCLUseInfo info, boolean isUsing, boolean fromTrigger)
    {
        ConjurerUseInfo cInfo = EUIUtils.safeCast(info, ConjurerUseInfo.class);
        if (cInfo == null)
        {
            return false;
        }
        return fields.random ^ (fields.affinities.isEmpty() ? cInfo.reactions.hasCombust() : EUIUtils.all(fields.affinities, cInfo.reactions::hasCombust));
    }

    @Override
    public void onElementReact(AffinityReactions reactions, AbstractCreature abstractCreature)
    {
        if (fields.affinities.isEmpty() ? reactions.hasCombust() : EUIUtils.all(fields.affinities, reactions::hasCombust))
        {
            useFromTrigger(makeInfo(abstractCreature));
        }
    }

    @Override
    public String getSubText()
    {
        if (hasParentType(PTrigger.class))
        {
            return TEXT.cond_wheneverYou(fields.affinities.isEmpty() ? ConjurerResources.conjurer.tooltips.combust.title : EUIRM.strings.verbNoun(ConjurerResources.conjurer.tooltips.combust.title, fields.getAffinityLevelOrString()));
        }
        String base = fields.affinities.isEmpty() ? ConjurerResources.conjurer.tooltips.combust.title : EUIRM.strings.adjNoun(fields.getAffinityLevelOrString(), ConjurerResources.conjurer.tooltips.combust.title);
        return fields.random ? TEXT.cond_not(base) : base;
    }
}

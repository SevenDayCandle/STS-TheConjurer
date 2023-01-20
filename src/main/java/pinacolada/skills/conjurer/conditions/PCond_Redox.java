package pinacolada.skills.conjurer.conditions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import extendedui.EUIRM;
import extendedui.EUIUtils;
import pinacolada.cards.base.AffinityReactions;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.misc.PCLUseInfo;
import pinacolada.interfaces.subscribers.OnElementReactSubscriber;
import pinacolada.resources.conjurer.ConjurerEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.PTrigger;
import pinacolada.skills.fields.PField_Affinity;

public class PCond_Redox extends PCond<PField_Affinity> implements OnElementReactSubscriber
{

    public static final PSkillData<PField_Affinity> DATA = register(PCond_Redox.class, PField_Affinity.class, 1, 1)
            .setColors(ConjurerEnum.Cards.THE_CONJURER)
            .selfTarget();

    public PCond_Redox()
    {
        this((PCLAffinity) null);
    }

    public PCond_Redox(PCLAffinity... affinities)
    {
        super(DATA, PCLCardTarget.None, 0);
        fields.setAffinity(affinities);
    }

    public PCond_Redox(PSkillSaveData content)
    {
        super(DATA, content);
    }

    @Override
    public boolean checkCondition(PCLUseInfo info, boolean isUsing, boolean fromTrigger)
    {
        return fields.random ^ (fields.affinities.isEmpty() ? info.reactions.hasRedox() : EUIUtils.all(fields.affinities, info.reactions::hasRedox));
    }

    @Override
    public void onElementReact(AffinityReactions reactions, AbstractCreature abstractCreature)
    {
        if (fields.affinities.isEmpty() ? reactions.hasRedox() : EUIUtils.all(fields.affinities, reactions::hasRedox))
        {
            useFromTrigger(makeInfo(abstractCreature));
        }
    }

    @Override
    public String getSubText()
    {
        if (hasParentType(PTrigger.class))
        {
            return TEXT.conditions.wheneverYou(fields.affinities.isEmpty() ? ConjurerResources.conjurer.tooltips.redox.title : EUIRM.strings.verbNoun(ConjurerResources.conjurer.tooltips.redox.title, fields.getAffinityLevelOrString()));
        }
        String base = fields.affinities.isEmpty() ? ConjurerResources.conjurer.tooltips.redox.title : EUIRM.strings.adjNoun(fields.getAffinityLevelOrString(), ConjurerResources.conjurer.tooltips.redox.title);
        return fields.random ? TEXT.conditions.not(base) : base;
    }
}

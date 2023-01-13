package pinacolada.skills.conjurer.conditions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import extendedui.EUIRM;
import extendedui.EUIUtils;
import pinacolada.cards.base.AffinityReactions;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.cards.base.PCLUseInfo;
import pinacolada.resources.conjurer.ConjurerEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.*;

public class PCond_Redox extends PCond
{

    public static final PSkillData DATA = register(PCond_Redox.class, PField_Empty.class, 1, 1)
            .setColors(ConjurerEnum.Cards.THE_CONJURER)
            .selfTarget();

    public PCond_Redox()
    {
        this((PCLAffinity) null);
    }

    public PCond_Redox(PCLAffinity... affinities)
    {
        super(DATA, PCLCardTarget.None, 0, affinities);
    }

    public PCond_Redox(PSkillSaveData content)
    {
        super(DATA, content);
    }

    public PCond_Redox(PSkill effect)
    {
        this();
        setChild(effect);
    }

    public PCond_Redox(PSkill... effect)
    {
        this();
        setChild(effect);
    }

    @Override
    public boolean checkCondition(PCLUseInfo info, boolean isUsing, boolean fromTrigger)
    {
        return alt ^ (affinities.isEmpty() ? info.reactions.hasRedox() : EUIUtils.all(affinities, info.reactions::hasRedox));
    }

    @Override
    public boolean triggerOnElementReact(AffinityReactions reactions, AbstractCreature target)
    {
        if (affinities.isEmpty() ? reactions.hasRedox() : EUIUtils.all(affinities, reactions::hasRedox))
        {
            if (this.childEffect != null)
            {
                this.childEffect.use(makeInfo(target));
            }
            return true;
        }
        return false;
    }

    @Override
    public String getSubText()
    {
        if (hasParentType(PTrigger.class))
        {
            return TEXT.conditions.wheneverYou(affinities.isEmpty() ? ConjurerResources.conjurer.tooltips.redox.title : EUIRM.strings.verbNoun(ConjurerResources.conjurer.tooltips.redox.title, getAffinityLevelOrString()));
        }
        String base = affinities.isEmpty() ? ConjurerResources.conjurer.tooltips.redox.title : EUIRM.strings.adjNoun(getAffinityLevelOrString(), ConjurerResources.conjurer.tooltips.redox.title);
        return alt ? TEXT.conditions.not(base) : base;
    }
}

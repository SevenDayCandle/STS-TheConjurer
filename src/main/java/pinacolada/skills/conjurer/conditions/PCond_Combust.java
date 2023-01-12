package pinacolada.skills.conjurer.conditions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import extendedui.EUIRM;
import extendedui.EUIUtils;
import pinacolada.cards.base.AffinityReactions;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.cards.base.PCLUseInfo;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.*;

public class PCond_Combust extends PCond
{

    public static final PSkillData DATA = register(PCond_Combust.class, PField_Empty.class, 1, 1)
            .setColors(ConjurerEnum.Cards.THE_CONJURER)
            .selfTarget();

    public PCond_Combust()
    {
        this((PCLAffinity) null);
    }

    public PCond_Combust(PCLAffinity... affinities)
    {
        super(DATA, PCLCardTarget.None, 0, affinities);
    }

    public PCond_Combust(PSkillSaveData content)
    {
        super(content);
    }

    public PCond_Combust(PSkill effect)
    {
        this();
        setChild(effect);
    }

    public PCond_Combust(PSkill... effect)
    {
        this();
        setChild(effect);
    }

    @Override
    public boolean checkCondition(PCLUseInfo info, boolean isUsing, boolean fromTrigger)
    {
        return alt ^ (affinities.isEmpty() ? info.reactions.hasCombust() : EUIUtils.all(affinities, info.reactions::hasCombust));
    }

    @Override
    public boolean triggerOnElementReact(AffinityReactions reactions, AbstractCreature target)
    {
        if (affinities.isEmpty() ? reactions.hasCombust() : EUIUtils.all(affinities, reactions::hasCombust))
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
            return TEXT.conditions.wheneverYou(affinities.isEmpty() ? ConjurerResources.conjurer.tooltips.combust.title : EUIRM.strings.verbNoun(ConjurerResources.conjurer.tooltips.combust.title, getAffinityLevelOrString()));
        }
        String base = affinities.isEmpty() ? ConjurerResources.conjurer.tooltips.combust.title : EUIRM.strings.adjNoun(getAffinityLevelOrString(), ConjurerResources.conjurer.tooltips.combust.title);
        return alt ? TEXT.conditions.not(base) : base;
    }
}

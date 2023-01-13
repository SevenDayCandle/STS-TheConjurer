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
import pinacolada.skills.PCond;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.PTrigger;
import pinacolada.skills.fields.PField_Affinity;

public class PCond_Combust extends PCond<PField_Affinity>
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
        return fields.random ^ (fields.affinities.isEmpty() ? info.reactions.hasCombust() : EUIUtils.all(fields.affinities, info.reactions::hasCombust));
    }

    @Override
    public boolean triggerOnElementReact(AffinityReactions reactions, AbstractCreature target)
    {
        if (fields.affinities.isEmpty() ? reactions.hasCombust() : EUIUtils.all(fields.affinities, reactions::hasCombust))
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
            return TEXT.conditions.wheneverYou(fields.affinities.isEmpty() ? ConjurerResources.conjurer.tooltips.combust.title : EUIRM.strings.verbNoun(ConjurerResources.conjurer.tooltips.combust.title, fields.getAffinityLevelOrString()));
        }
        String base = fields.affinities.isEmpty() ? ConjurerResources.conjurer.tooltips.combust.title : EUIRM.strings.adjNoun(fields.getAffinityLevelOrString(), ConjurerResources.conjurer.tooltips.combust.title);
        return fields.random ? TEXT.conditions.not(base) : base;
    }
}

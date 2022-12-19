package pinacolada.skills.conjurer.conditions;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import extendedui.EUIRM;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.cards.base.PCLUseInfo;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerEnum;
import pinacolada.skills.PCond;
import pinacolada.skills.PSkill;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;

public class PCond_Intensify extends PCond
{

    public static final PSkillData DATA = register(PCond_Intensify.class, PCLEffectType.General, 1, 1)
            .setColors(ConjurerEnum.Cards.THE_CONJURER)
            .selfTarget();

    public PCond_Intensify()
    {
        this((PCLAffinity) null);
    }

    public PCond_Intensify(PCLAffinity... affinities)
    {
        super(DATA, PCLCardTarget.None, 0, affinities);
    }

    public PCond_Intensify(PSkillSaveData content)
    {
        super(content);
    }

    public PCond_Intensify(PSkill effect)
    {
        this();
        setChild(effect);
    }

    public PCond_Intensify(PSkill... effect)
    {
        this();
        setChild(effect);
    }

    // This should not activate the child effect when played normally

    @Override
    public String getSubText()
    {
        return TEXT.conditions.wheneverYou(affinities.isEmpty() ? PGR.core.tooltips.level.title : EUIRM.strings.verbNoun(PGR.core.tooltips.level.title, getAffinityLevelOrString()));
    }

    @Override
    public void use(PCLUseInfo info)
    {
    }

    @Override
    public void use(PCLUseInfo info, int index)
    {
    }

    @Override
    public boolean canPlay(AbstractCard card, AbstractMonster m)
    {
        return true;
    }

    @Override
    public boolean triggerOnIntensify(PCLAffinity affinity)
    {
        if (affinities.isEmpty() || affinities.contains(affinity))
        {
            if (this.childEffect != null)
            {
                this.childEffect.use(makeInfo(null).setData(affinity));
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean checkCondition(PCLUseInfo info, boolean isUsing, boolean fromTrigger)
    {
        return false;
    }
}

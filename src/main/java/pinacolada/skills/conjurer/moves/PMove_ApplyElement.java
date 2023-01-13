package pinacolada.skills.conjurer.moves;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import extendedui.utilities.ColoredString;
import pinacolada.actions.powers.ApplyElementalDebuff;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.cards.base.PCLUseInfo;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.utilities.GameUtilities;

public class PMove_ApplyElement extends PMove
{
    public static final PSkillData DATA = register(PMove_ApplyElement.class, PCLEffectType.Power, -999, 999, ConjurerEnum.Cards.THE_CONJURER);

    public PMove_ApplyElement()
    {
        this(PCLCardTarget.Self, 1);
    }

    public PMove_ApplyElement(PSkillSaveData content)
    {
        super(DATA, content);
    }

    public PMove_ApplyElement(PCLCardTarget target, int amount, PCLAffinity... affinities)
    {
        super(DATA, target, amount, affinities);
    }

    @Override
    public void onDrag(AbstractMonster m)
    {
        if (m != null)
        {
            for (PCLAffinity affinity : affinities)
            {
                GameUtilities.getIntent(m).addModifier(PCLElementHelper.get(affinity).ID, amount);
            }
        }
    }

    @Override
    public ColoredString getColoredValueString()
    {
        return target == PCLCardTarget.Self ? getColoredValueString(Math.abs(baseAmount), Math.abs(amount)) : super.getColoredValueString();
    }

    @Override
    public String getSampleText()
    {
        return TEXT.actions.applyAmount("X", TEXT.cardEditor.powers);
    }

    @Override
    public boolean isDetrimental()
    {
        return target.targetsSelf() || target.targetsAllies();
    }

    @Override
    public void use(PCLUseInfo info)
    {
        if (alt)
        {
            PCLAffinity power = GameUtilities.getRandomElement(affinities);
            if (power != null)
            {
                getActions().add(new ApplyElementalDebuff(info.source, info.target, target, power, amount));
            }
        }
        else if (!affinities.isEmpty())
        {
            for (PCLAffinity power : affinities)
            {
                getActions().add(new ApplyElementalDebuff(info.source, info.target, target, power, amount));
            }
        }
        else
        {
            for (int i = 0; i < amount; i++)
            {
                getActions().add(new ApplyElementalDebuff(info.source, info.target, target, PCLElementHelper.random(), amount));
            }
        }
        super.use(info);

    }

    @Override
    public String getSubText()
    {
        String joinedString;
        if (alt && !powers.isEmpty())
        {
            joinedString = PCLElementHelper.getPowerOrString(affinities);
            switch (target)
            {
                case RandomEnemy:
                case AllEnemy:
                case All:
                    return TEXT.subjects.randomly(powers.size() > 0 && powers.get(0).isDebuff ? TEXT.actions.applyAmountToTarget(getAmountRawString(), joinedString, getTargetString()) : TEXT.actions.giveTargetAmount(getTargetString(), getAmountRawString(), joinedString));
                case Single:
                    return TEXT.subjects.randomly(powers.size() > 0 && powers.get(0).isDebuff ? TEXT.actions.applyAmount(getAmountRawString(), joinedString) : TEXT.actions.giveTargetAmount(getTargetString(), getAmountRawString(), joinedString));
                default:
                    return TEXT.subjects.randomly(amount < 0 ? TEXT.actions.loseAmount(getAmountRawString(), joinedString)
                            : TEXT.actions.gainAmount(getAmountRawString(), joinedString));
            }
        }
        joinedString = powers.isEmpty() ? TEXT.subjects.randomX(plural(ConjurerResources.conjurer.tooltips.elementalDebuff)) : PCLElementHelper.getPowerString(affinities);
        switch (target)
        {
            case RandomEnemy:
            case AllEnemy:
            case All:
                return powers.size() > 0 && powers.get(0).isDebuff ? TEXT.actions.applyAmountToTarget(getAmountRawString(), joinedString, getTargetString()) : TEXT.actions.giveTargetAmount(getTargetString(), getAmountRawString(), joinedString);
            case Single:
                return powers.size() > 0 && powers.get(0).isDebuff ? TEXT.actions.applyAmount(getAmountRawString(), joinedString) : TEXT.actions.giveTargetAmount(getTargetString(), getAmountRawString(), joinedString);
            default:
                return amount < 0 ? TEXT.actions.loseAmount(getAmountRawString(), joinedString)
                        : TEXT.actions.gainAmount(getAmountRawString(), joinedString);
        }
    }
}

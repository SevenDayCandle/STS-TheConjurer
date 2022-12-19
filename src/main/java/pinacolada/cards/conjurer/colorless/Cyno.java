package pinacolada.cards.conjurer.colorless;

import pinacolada.cards.base.*;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerEnum;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.skills.skills.PCustomCond;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.skills.skills.base.conditions.PCond_HaveExhausted;
import pinacolada.utilities.GameUtilities;
import pinacolada.utilities.RandomizedList;

import java.util.ArrayList;

public class Cyno extends PCLCard
{
    public static final PCLCardData DATA = register(Cyno.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON, PCLAttackType.Piercing)
            .setDamage(4, array(2, -2), 2, array(0,0))
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green, PCLAffinity.Orange, PCLAffinity.Dark)
            .setColorless()
            .setMultiformData(2)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Cyno()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove().setDamageEffect(PCLEffekseerEFX.SWORD18);
        addUseMove(new CynoCond(DATA, 2, 4).setUpgrade(0, 1).setUpgradeExtra(0, 1));
        addUseMove(new PCond_HaveExhausted(), getSpecialMove(1, this::action, 1));
    }

    public void action(PSpecialSkill move, PCLUseInfo info)
    {
        for (PSkill effect : move.source.getEffects())
        {
            if (effect instanceof CynoCond)
            {
                for (PSkill power : ((CynoCond) effect).debuffs)
                {
                    power.addAmountForCombat(1);
                }
            }
        }
    }

    protected static class CynoCond extends PCustomCond
    {
        public ArrayList<PSkill> debuffs;

        public CynoCond(PCLCardData data, int amount, int choices)
        {
            super(data, 0, amount, choices);
        }

        protected void useImpl(PCLUseInfo info)
        {
            RandomizedList<PSkill> choices = new RandomizedList<>(getDebuffs());
            while (choices.size() > extra)
            {
                choices.retrieve(rng, true);
            }
            getActions().tryChooseSkill(getPCLSource().cardData, amount, info.source, info.target, choices.getInnerList());
        }

        protected ArrayList<PSkill> getDebuffs()
        {
            if (debuffs == null)
            {
                debuffs = new ArrayList<>();
                debuffs.add(PMove.applyToSingle(2, PCLPowerHelper.Vulnerable));
                debuffs.add(PMove.applyToSingle(2, PCLPowerHelper.Weak));
                debuffs.add(PMove.applyToSingle(3, PCLPowerHelper.Shackles));
                debuffs.add(PMove.applyToSingle(3, PCLPowerHelper.Poison));

                if (GameUtilities.getPlayerClass() == ConjurerEnum.Characters.THE_CONJURER)
                {
                    debuffs.add(PMove.applyToSingle(3, PCLPowerHelper.Burning));
                    debuffs.add(PMove.applyToSingle(3, PCLPowerHelper.Flowed));
                    debuffs.add(PMove.applyToSingle(3, PCLPowerHelper.Stoned));
                }
                else
                {
                    debuffs.add(PMove.applyToSingle(2, PCLPowerHelper.Blinded));
                    debuffs.add(PMove.applyToSingle(3, PCLPowerHelper.Blasted));
                }
            }
            return debuffs;
        }
    }
}

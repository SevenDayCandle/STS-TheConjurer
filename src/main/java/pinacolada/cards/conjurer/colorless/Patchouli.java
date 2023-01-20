package pinacolada.cards.conjurer.colorless;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.*;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerEnum;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.skills.skills.PCustomCond;
import pinacolada.utilities.GameUtilities;
import pinacolada.utilities.RandomizedList;

import java.util.ArrayList;

@VisibleCard
public class Patchouli extends PCLCard
{
    public static final PCLCardData DATA = register(Patchouli.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.RARE, PCLAttackType.Magical)
            .setDamage(4, 1)
            .setPriority(1)
            .setHp(9, 1)
            .setAffinities(2, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.touhouProject)
            .setColorless();

    public Patchouli()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.ELECTRIC);
        addUseMove(PCond.cooldown(2), new PatchouliCond(DATA, 2, 4).setUpgrade(1).setUpgradeExtra(1));
    }

    protected static class PatchouliCond extends PCustomCond
    {
        public ArrayList<PSkill<?>> debuffs;

        public PatchouliCond(PCLCardData data, int amount, int choices)
        {
            super(data, 0, amount, choices);
        }

        protected void useImpl(PCLUseInfo info)
        {
            RandomizedList<PSkill<?>> choices = new RandomizedList<>(getDebuffs());
            while (choices.size() > extra)
            {
                choices.retrieve(rng, true);
            }
            getActions().tryChooseSkill(getPCLSource().cardData, amount, info.source, info.target, choices);
        }

        protected ArrayList<PSkill<?>> getDebuffs()
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
                    debuffs.add(PMove.applyToSingle(3, PCLElementHelper.Ignis));
                    debuffs.add(PMove.applyToSingle(3, PCLElementHelper.Gelus));
                    debuffs.add(PMove.applyToSingle(3, PCLElementHelper.Aer));
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

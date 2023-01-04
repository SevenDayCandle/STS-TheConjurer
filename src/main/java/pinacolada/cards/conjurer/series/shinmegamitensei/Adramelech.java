package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.cards.base.*;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class Adramelech extends PCLCard
{
    public static final PCLCardData DATA = register(Adramelech.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.UNCOMMON, PCLAttackType.Normal, PCLCardTarget.RandomEnemy)
            .setDamage(3, 1)
            .setPriority(1)
            .setHp(10, 2)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Adramelech()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.FIRE);
        addUseMove(PCond.cooldown(0), PMove.applyToRandom(3, PCLElementHelper.Burned, PCLElementHelper.Flowed, PCLElementHelper.Stoned).setAlt(true));
    }
}

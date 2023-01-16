package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.*;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

public class MagmaFlare extends PCLCard
{
    public static final PCLCardData DATA = register(MagmaFlare.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.COMMON, PCLAttackType.Ranged, PCLCardTarget.AllEnemy)
            .setDamage(13, 2)
            .setAffinities(PCLAffinity.Red)
            .setCore();

    public MagmaFlare()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.SMALL_EXPLOSION);
        addUseMove(PMove.applyToEnemies(2, PCLElementHelper.Ignis).setUpgrade(1));
    }
}

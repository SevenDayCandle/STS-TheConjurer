package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.*;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

public class Albedo extends PCLCard
{
    public static final PCLCardData DATA = register(Albedo.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Magical)
            .setDamage(3, 1)
            .setPriority(1)
            .setHp(7, 2)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Orange, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Albedo()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.EARTH);
        addUseMove(PCond.cooldown(2), PMultiSkill.choose(
                PMove.applyToEnemies(6, PCLElementHelper.Stoned),
                PMove.stabilize(PCLCardTarget.AllEnemy, PCLElementHelper.Chilled)
        ));
    }
}

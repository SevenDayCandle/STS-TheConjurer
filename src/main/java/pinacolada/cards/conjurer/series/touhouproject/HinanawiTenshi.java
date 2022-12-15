package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.cards.base.*;
import pinacolada.effects.AttackEffects;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrait;
import pinacolada.skills.skills.base.modifiers.PMod_PerOrb;

public class HinanawiTenshi extends PCLCard
{
    public static final PCLCardData DATA = register(HinanawiTenshi.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON, PCLAttackType.Ranged, PCLCardTarget.RandomEnemy)
            .setDamage(3, 0, 2)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public HinanawiTenshi()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.EARTH);
        addUseMove(new PMod_PerOrb(1, PCLOrbHelper.Earth), PTrait.hasDamage(2).setUpgrade(1));
        addUseMove(PCond.redox(), PMove.channelOrb(1, PCLOrbHelper.Earth));
    }
}

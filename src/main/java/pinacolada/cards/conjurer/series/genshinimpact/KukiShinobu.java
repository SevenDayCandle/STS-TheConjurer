package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class KukiShinobu extends PCLCard
{
    public static final PCLCardData DATA = register(KukiShinobu.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Ranged, PCLCardTarget.RandomEnemy)
            .setDamage(2, 0, 2)
            .setPriority(1)
            .setHp(5, 1)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public KukiShinobu()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.DAGGER);
        addUseMove(PCond.cooldown(0), PMove.discardRandom(1), PMove.applyToRandom(5, PCLPowerHelper.Poison).setUpgrade(2));
    }
}

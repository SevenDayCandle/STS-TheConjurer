package pinacolada.cards.conjurer.series.persona;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLAttackType;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class AnnTakamaki extends PCLCard
{
    public static final PCLCardData DATA = register(AnnTakamaki.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON, PCLAttackType.Ranged)
            .setDamage(7, 2, 2)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.persona);

    public AnnTakamaki()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.GUNSHOT);
        addUseMove(PCond.cooldown(2), PMove.selfTransform(AnnTakamaki_Carmen.DATA));
        addUseMove(PCond.redox(), PMove.channelOrb(1, PCLOrbHelper.Fire));
    }
}

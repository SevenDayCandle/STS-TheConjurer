package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLAttackType;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class MarisaKirisame extends PCLCard
{
    public static final PCLCardData DATA = register(MarisaKirisame.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON, PCLAttackType.Magical)
            .setDamage(6, 3)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Light)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public MarisaKirisame()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.LIGHTNING);
        addUseMove(PMove.channelOrb(1, PCLOrbHelper.Air));
        addUseMove(PCond.onExhaust(), PMove.gain(1, PCLPowerHelper.Sorcery));
    }
}

package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLAttackType;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiCond;
import pinacolada.skills.skills.base.conditions.PCond_EvokeOrb;

public class Ningguang extends PCLCard
{
    public static final PCLCardData DATA = register(Ningguang.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.UNCOMMON, PCLAttackType.Magical)
            .setDamage(5, 3)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Ningguang()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove().setDamageEffect(PCLEffekseerEFX.BLOW06);
        addUseMove(PCond.ifElse(PMove.gainTemporary(4, PCLPowerHelper.Resistance), PMove.channelOrb(1, PCLOrbHelper.Earth), new PCond_EvokeOrb(1, PCLOrbHelper.Earth)));
        addUseMove(PMultiCond.or(PCond.redox(), PCond.onReshuffle()), PMove.triggerOrb(1, 0, PCLOrbHelper.Earth));
    }
}

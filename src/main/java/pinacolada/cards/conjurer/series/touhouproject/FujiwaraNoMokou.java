package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.conditions.PCond_EvokeOrb;

public class FujiwaraNoMokou extends PCLCard
{
    public static final PCLCardData DATA = register(FujiwaraNoMokou.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.UNCOMMON)
            .setDamage(9, array(3, 0))
            .setAffinities(PCLAffinity.Red)
            .setMultiformData(2)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public FujiwaraNoMokou()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove().setDamageEffect(PCLEffekseerEFX.SPEAR01);
        addUseMove(PCond.ifElse(PMove.gain(3, PCLPowerHelper.Vigor).setUpgrade(0, 3), PMove.channelOrb(1, PCLOrbHelper.Fire).setUpgrade(0, 1), new PCond_EvokeOrb(1, PCLOrbHelper.Fire)));
        addUseMove(PCond.limited(), PCond.onExhaust(), PMove.fetch(1));
    }
}

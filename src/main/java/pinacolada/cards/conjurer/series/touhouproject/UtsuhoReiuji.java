package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiCond;
import pinacolada.skills.skills.base.modifiers.PMod_EvokePerOrb;

public class UtsuhoReiuji extends PCLCard
{
    public static final PCLCardData DATA = register(UtsuhoReiuji.class, ConjurerResources.conjurer)
            .setAttack(3, CardRarity.RARE, PCLAttackType.Ranged, PCLCardTarget.RandomEnemy)
            .setDamage(10, array(4, -9))
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green)
            .setTags(PCLCardTag.Exhaust)
            .setMultiformData(2)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public UtsuhoReiuji()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove().setDamageEffect(PCLEffekseerEFX.FIRE11);
        addUseMove(new PMod_EvokePerOrb(1), PMove.channelOrb(1, PCLOrbHelper.Fire, PCLOrbHelper.Air).setUpgrade(0, 1).setAlt(true));
        addUseMove(PMultiCond.or(PCond.redox(), PCond.onExhaust()), PMove.applyToEnemies(3, PCLPowerHelper.Burning));
    }
}

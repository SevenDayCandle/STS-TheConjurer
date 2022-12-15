package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.*;
import pinacolada.effects.AttackEffects;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.modifiers.PMod_PerPower;
import pinacolada.skills.skills.base.traits.PTrait_Damage;

public class Xingqiu extends PCLCard
{
    public static final PCLCardData DATA = register(Xingqiu.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON, PCLAttackType.Piercing)
            .setDamage(5, array(3, 0))
            .setMultiformData(2)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Xingqiu()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.NONE).setDamageEffect(PCLEffekseerEFX.SWORD18);
        addUseMove(new PMod_PerPower(PCLCardTarget.Single, 1, PCLPowerHelper.Burning), new PTrait_Damage(2).setUpgrade(0, 2));
        addUseMove(PCond.redox(), PMove.channelOrb(1, PCLOrbHelper.Water));
    }
}

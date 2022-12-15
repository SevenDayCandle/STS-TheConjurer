package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.moves.PMove_SpreadPower;

public class KazuhaKaedehara extends PCLCard
{
    public static final PCLCardData DATA = register(KazuhaKaedehara.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.UNCOMMON, PCLAttackType.Piercing)
            .setDamage(6, array(2, 1))
            .setTags(
                    PCLCardTag.Haste.make(0, array(0, 2))
            )
            .setMultiformData(2)
            .setAffinities(2, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public KazuhaKaedehara()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove().setDamageEffect(PCLEffekseerEFX.SWORD07);
        addUseMove(PMove.applyToSingle(1, PCLPowerHelper.Flowed, PCLPowerHelper.Poison).setUpgrade(2, 0),
                new PMove_SpreadPower(PCLCardTarget.Single, 0, PCLPowerHelper.Flowed, PCLPowerHelper.Poison)
                        .setCustomUpgrade((s, f, u) -> s.setPower(f > 0 ? array(PCLPowerHelper.Flowed, PCLPowerHelper.Poison, PCLPowerHelper.Burning, PCLPowerHelper.Chilled) : array(PCLPowerHelper.Flowed, PCLPowerHelper.Poison))));
        addUseMove(PCond.semiLimited(), PCond.onDiscard(), PMove.draw(1));
    }
}

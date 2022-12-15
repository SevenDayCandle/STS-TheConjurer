package pinacolada.cards.conjurer.series.persona;

import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

public class RyujiSakamoto_CaptainKidd extends PCLCard
{
    public static final PCLCardData DATA = register(RyujiSakamoto_CaptainKidd.class, ConjurerResources.conjurer)
            .setAttack(0, CardRarity.SPECIAL, PCLAttackType.Normal, PCLCardTarget.AllEnemy)
            .setDamage(10, 0)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Orange)
            .setTags(PCLCardTag.Retain, PCLCardTag.Exhaust)
            .setLoadout(ConjurerPlayerData.persona);

    public RyujiSakamoto_CaptainKidd()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove().setDamageEffect(PCLEffekseerEFX.BLOW03);
        addUseMove(PMove.applyToEnemies(2, PCLPowerHelper.Weak, PCLPowerHelper.Vulnerable).setUpgrade(1));
        addUseMove(PMultiSkill.choose(
                PMove.obtain(RyujiSakamoto.DATA),
                PMove.gain(5, PCLPowerHelper.Vigor)
        ));
    }
}

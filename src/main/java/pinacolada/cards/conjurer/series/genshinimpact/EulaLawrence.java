package pinacolada.cards.conjurer.series.genshinimpact;

import com.badlogic.gdx.graphics.Color;
import pinacolada.cards.base.*;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrigger;
import pinacolada.skills.skills.base.conditions.PCond_CheckPower;

public class EulaLawrence extends PCLCard
{
    public static final PCLCardData DATA = register(EulaLawrence.class, ConjurerResources.conjurer)
            .setAttack(3, CardRarity.UNCOMMON, PCLAttackType.Normal)
            .setDamage(17, 3)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public EulaLawrence()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove().setDamageEffect(PCLEffekseerEFX.BLOW03, Color.SKY);
        addGainPower(2, PTrigger.when(new PCond_CheckPower(PCLCardTarget.AllEnemy, 1, PCLPowerHelper.Chilled), PMove.applyToRandom(1, PCLPowerHelper.Vulnerable)));
        addUseMove(new PCond_CheckPower(PCLCardTarget.AllEnemy, 1, PCLPowerHelper.Vulnerable), PMove.dealDamageToAll(8));
    }
}

package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrait;
import pinacolada.skills.skills.PDelay;
import pinacolada.skills.skills.base.conditions.PCond_CheckBlock;
import pinacolada.skills.skills.base.modifiers.PMod_PerPower;

public class IttoArataki extends PCLCard
{
    public static final PCLCardData DATA = register(IttoArataki.class, ConjurerResources.conjurer)
            .setAttack(3, CardRarity.UNCOMMON)
            .setDamage(12, 1)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public IttoArataki()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove().setDamageEffect(PCLEffekseerEFX.BLOW04);
        addUseMove(new PMod_PerPower(PCLCardTarget.Single, 1, PCLPowerHelper.Stoned, PCLPowerHelper.Vulnerable), PTrait.hasDamage(2).setUpgrade(1));
        addUseMove(new PCond_CheckBlock(PCLCardTarget.Single, 0), PDelay.turnStartLast(1), PMove.applyToRandom(5, PCLPowerHelper.Stoned));
    }
}

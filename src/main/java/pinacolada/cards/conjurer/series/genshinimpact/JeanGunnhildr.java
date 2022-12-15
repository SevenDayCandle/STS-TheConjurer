package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.modifiers.PMod_DiscardPerCard;

public class JeanGunnhildr extends PCLCard
{
    public static final PCLCardData DATA = register(JeanGunnhildr.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.RARE, PCLAttackType.Normal)
            .setDamage(7, 2)
            .setBlock(2, 1)
            .setTags(PCLCardTag.Loyal)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange, PCLAffinity.Light)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public JeanGunnhildr()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove().setDamageEffect(PCLEffekseerEFX.SWORD06);
        addUseMove(new PMod_DiscardPerCard(2, PCLCardGroupHelper.Hand), PMove.gain(-1, PCLPowerHelper.Vulnerable, PCLPowerHelper.Frail));
        addUseMove(PCond.semiLimited(), PCond.onDiscard(), PMove.applyToEnemies(2, PCLPowerHelper.Flowed));
    }
}

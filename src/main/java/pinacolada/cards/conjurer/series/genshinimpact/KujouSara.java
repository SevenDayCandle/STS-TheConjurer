package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.*;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.modifiers.PMod_DiscardPerCard;
import pinacolada.skills.skills.base.moves.PMove_StabilizePower;

public class KujouSara extends PCLCard
{
    public static final PCLCardData DATA = register(KujouSara.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.UNCOMMON, PCLAttackType.Piercing)
            .setDamage(7, 3)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public KujouSara()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.CLAW);
        addUseMove(new PMod_DiscardPerCard(3, PCLCardGroupHelper.Hand).setAffinity(PCLAffinity.Green, PCLAffinity.Orange).setAlt2(true), PMove.applyToEnemies(2, PCLPowerHelper.Flowed));
        addUseMove(PCond.redox().setAlt(true), new PMove_StabilizePower(PCLCardTarget.Single, PCLPowerHelper.Flowed));
    }
}

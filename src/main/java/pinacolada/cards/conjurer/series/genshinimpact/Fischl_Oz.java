package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.special.moves.PMove_ExhaustAlly;

@VisibleCard
public class Fischl_Oz extends PCLCard {
    public static final PCLCardData DATA = register(Fischl_Oz.class, ConjurerResources.conjurer)
            .setSummon(0, CardRarity.SPECIAL, PCLAttackType.Immaterial, PCLCardTarget.AllEnemy)
            .setDamage(4, 1)
            .setHp(3, 2)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.genshinImpact, true);

    public Fischl_Oz() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.GHOST);
        addUseMove(PCond.onWithdraw(), PMove.applyToEnemies(3, PCLPowerHelper.Blinded), new PMove_ExhaustAlly(PCLCardTarget.Self, 1));
    }
}

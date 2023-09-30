package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.moves.PMove_KillAlly;

@VisibleCard
public class AnnTakamaki_Carmen extends PCLCard {
    public static final PCLCardData DATA = register(AnnTakamaki_Carmen.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.SPECIAL, PCLAttackType.Ranged, PCLCardTarget.RandomEnemy)
            .setDamage(7, 1, 3)
            .setHp(8, 1)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public AnnTakamaki_Carmen() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.BLUNT_LIGHT);
        addUseMove(PMove.applyToEnemies(6, IgnisPower.DATA), new PMove_KillAlly(PCLCardTarget.Self, 1));
    }
}

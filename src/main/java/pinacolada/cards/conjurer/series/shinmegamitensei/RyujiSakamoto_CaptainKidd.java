package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.moves.PMove_KillAlly;

@VisibleCard
public class RyujiSakamoto_CaptainKidd extends PCLCard {
    public static final PCLCardData DATA = register(RyujiSakamoto_CaptainKidd.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.SPECIAL)
            .setDamage(11, 3)
            .setHp(10, 1)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public RyujiSakamoto_CaptainKidd() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.BLUNT_LIGHT);
        addUseMove(PMove.applyToEnemies(2, PCLPowerData.Vulnerable, PCLPowerData.Weak), new PMove_KillAlly(PCLCardTarget.Self, 1));
    }
}

package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.moves.PMove_KillAlly;

@VisibleCard
public class HaruOkumura_Milady extends PCLCard {
    public static final PCLCardData DATA = register(HaruOkumura_Milady.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.SPECIAL, PCLAttackType.Immaterial)
            .setDamage(3, 1)
            .setHp(13, 1)
            .setAffinities(PCLAffinity.Orange, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public HaruOkumura_Milady() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.PSYCHOKINESIS);
        addUseMove(new PMove_KillAlly(PCLCardTarget.Self, 1), PMove.gainTempHP(7).setUpgrade(1));
    }
}

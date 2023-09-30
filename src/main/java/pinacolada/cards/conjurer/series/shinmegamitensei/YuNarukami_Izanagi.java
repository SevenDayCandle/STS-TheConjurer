package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class YuNarukami_Izanagi extends PCLCard {
    public static final PCLCardData DATA = register(YuNarukami_Izanagi.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.SPECIAL, PCLAttackType.Piercing, PCLCardTarget.AllEnemy)
            .setDamage(9, 2)
            .setHp(12, 3)
            .setAffinities(PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei, true);

    public YuNarukami_Izanagi() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SLASH_DIAGONAL);
        addUseMove(PCond.cooldown(0), PMove.applyToEveryone(2, PCLPowerData.Shackles));
        addUseMove(PCond.onSummon(), PMove.triggerAlly(PCLCardTarget.Self, 1));
    }
}

package pinacolada.cards.conjurer.series.honkai;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PDelay;
import pinacolada.skills.PMove;

@VisibleCard
public class Elysia extends PCLCard {
    public static final PCLCardData DATA = register(Elysia.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Immaterial)
            .setDamage(3, 1)
            .setHp(4, 2)
            .setAffinities(PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.honkai, true);

    public Elysia() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.ELECTRIC);
        addUseMove(PCond.onSummon(), PDelay.turnStart(1), PMove.applyToEveryone(1, PCLPowerHelper.Silenced));
    }
}

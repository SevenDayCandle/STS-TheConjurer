package pinacolada.cards.conjurer.colorless;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Jakk extends PCLCard {
    public static final PCLCardData DATA = register(Jakk.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE)
            .setDamage(3, 2)
            .setHp(6, 0)
            .setAffinities(PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.ragnarok, true);

    public Jakk() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.FIRE);
        addUseMove(PCond.cooldown(2), PMove.applyToEveryone(2, PCLPowerData.Strength));
    }
}
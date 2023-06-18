package pinacolada.cards.conjurer.special;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class JAX extends PCLCard {
    public static final PCLCardData DATA = register(JAX.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.SPECIAL, PCLAttackType.Immaterial)
            .setDamage(3, 2)
            .setHp(6, 0)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Purple)
            .setTags(PCLCardTag.Exhaust)
            .setLoadout(ConjurerPlayerData.ragnarok, true);

    public JAX() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.FIRE);
        addUseMove(PCond.cooldown(1), PMove.applyTemporaryToEveryone(4, PCLPowerHelper.Strength));
    }
}
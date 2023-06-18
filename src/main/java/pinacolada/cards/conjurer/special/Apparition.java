package pinacolada.cards.conjurer.special;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Apparition extends PCLCard {
    public static final PCLCardData DATA = register(Apparition.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.SPECIAL, PCLAttackType.Immaterial)
            .setDamage(1, 2)
            .setHp(1, 0)
            .setAffinities(PCLAffinity.Purple)
            .setTags(PCLCardTag.Ethereal)
            .setLoadout(ConjurerPlayerData.ragnarok, true);

    public Apparition() {
        super(DATA);
    }

    @Override
    public void setup(Object input) {
        addDamageMove();
        addUseMove(PCond.onDeath(), PMove.applyToEveryone(2, PCLPowerHelper.Intangible));
    }
}
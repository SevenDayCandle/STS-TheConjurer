package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.modifiers.PMod_PerCardDamage;

@VisibleCard
public class LifestealFist extends PCLCard {
    public static final PCLCardData DATA = register(LifestealFist.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.SPECIAL)
            .setDamage(3, 2)
            .setTags(PCLCardTag.Purge)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public LifestealFist() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.PUNCH);
        addUseMove(new PMod_PerCardDamage(1), PMove.heal(1));
    }
}

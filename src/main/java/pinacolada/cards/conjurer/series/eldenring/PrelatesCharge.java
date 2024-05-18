package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.ConjurerEFK;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.conjurer.modifiers.PMod_PerReaction;

@VisibleCard
public class PrelatesCharge extends PCLCard {
    public static final PCLCardData DATA = register(PrelatesCharge.class, ConjurerResources.conjurer)
            .setAttack(3, CardRarity.RARE, PCLAttackType.Normal)
            .setDamage(13, 3)
            .setAffinities(2, PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public PrelatesCharge() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(ConjurerEFK.SPEAR03).setBonus(new PMod_PerReaction(1), 2, 1);
    }
}

package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.conjurer.VentusPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class StormAssault extends PCLCard {
    public static final PCLCardData DATA = register(StormAssault.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.UNCOMMON)
            .setDamage(9, 3)
            .setAffinities(2, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public StormAssault() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(ConjurerEFK.MGC_Nature03).setBonus(PCond.haveDiscarded(), 5, 1);
        addUseMove(PMove.applyToSingle(2, VentusPower.DATA));
    }
}

package pinacolada.cards.conjurer.basic;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Geomorphology extends PCLCard {
    public static final PCLCardData DATA = register(Geomorphology.class, ConjurerResources.conjurer)
            .setPower(1, CardRarity.UNCOMMON)
            .setAffinities(2, PCLAffinity.Orange)
            .setCore();

    public Geomorphology() {
        super(DATA);
    }

    public void setup(Object input) {
        addGainPower(PTrigger.when(2, PCond.onOtherCardPlayed(CardType.POWER, PCLEnum.CardType.SUMMON), PMove.apply(PCLCardTarget.AllEnemy, 1, PCLElementHelper.Petra)).setUpgrade(1));
    }
}

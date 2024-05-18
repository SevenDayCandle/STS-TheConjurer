package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.FlowPower;
import pinacolada.powers.conjurer.VentusPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class StormWall extends PCLCard {
    public static final PCLCardData DATA = register(StormWall.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.COMMON, PCLCardTarget.Self)
            .setBlock(8, 4)
            .setAffinities(PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public StormWall() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
        addGainPower(1, PTrigger.when(PCond.haveTakenDamage(), PMove.applyToSingle(2, VentusPower.DATA)));
    }
}

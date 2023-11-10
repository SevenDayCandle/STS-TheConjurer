package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiCond;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Homeward extends PCLCard {
    public static final PCLCardData DATA = register(Homeward.class, ConjurerResources.conjurer)
            .setPower(0, CardRarity.UNCOMMON)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public Homeward() {
        super(DATA);
    }

    public void setup(Object input) {
        addGainPower(PTrigger.when(1,
                PMultiCond.or(PCond.onWithdraw(), PCond.onDiscard()),
                PMove.modifyCost(-1).useParentForce().edit(f -> f.setOr(true))).setUpgrade(1));
    }
}

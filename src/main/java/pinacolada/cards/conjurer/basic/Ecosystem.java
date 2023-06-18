package pinacolada.cards.conjurer.basic;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PBranchCond;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Ecosystem extends PCLCard {
    public static final PCLCardData DATA = register(Ecosystem.class, ConjurerResources.conjurer)
            .setPower(2, CardRarity.RARE)
            .setCostUpgrades(-1)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green, PCLAffinity.Orange)
            .setCore();

    public Ecosystem() {
        super(DATA);
    }

    public void setup(Object input) {
        addGainPower(PTrigger.when(
                PBranchCond.branch(PCond.checkLevel(1, PCLAffinity.Blue, PCLAffinity.Green, PCLAffinity.Orange),
                        PMove.gainEnergy(1),
                        PMove.draw(2),
                        PMove.gainTemporary(5, PCLPowerHelper.Malleable)
                )));
    }
}

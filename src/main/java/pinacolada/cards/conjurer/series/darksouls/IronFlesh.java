package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.ForgingPower;
import pinacolada.powers.conjurer.PetraPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrait;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class IronFlesh extends PCLCard {
    public static final PCLCardData DATA = register(IronFlesh.class, ConjurerResources.conjurer)
            .setPower(1, CardRarity.UNCOMMON)
            .setAffinities(2, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public IronFlesh() {
        super(DATA);
    }

    public void setup(Object input) {
        addGainPower(PTrigger.when(PCond.onTurnStart(), PMultiSkill.join(PMove.gain(3, ForgingPower.DATA).setUpgrade(2), PMove.gain(1, PetraPower.DATA))));
    }
}

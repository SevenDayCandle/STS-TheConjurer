package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.PetraPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Cragblade extends PCLCard {
    public static final PCLCardData DATA = register(Cragblade.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.UNCOMMON, PCLCardTarget.Self)
            .setAffinities(1, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public Cragblade() {
        super(DATA);
    }

    public void setup(Object input) {
        addGainPower(2, PTrigger.when(PCond.damage(PCLCardTarget.Self, 1), PMultiSkill.join(PMove.applyToSingle(1, PetraPower.DATA), PMove.gainBlock(5).setUpgrade(1))));
    }
}

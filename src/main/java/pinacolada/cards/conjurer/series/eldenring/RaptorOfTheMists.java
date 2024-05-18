package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

@VisibleCard
public class RaptorOfTheMists extends PCLCard {
    public static final PCLCardData DATA = register(RaptorOfTheMists.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Self)
            .setBlock(4, 2, 2)
            .setAffinities(PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public RaptorOfTheMists() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
        addUseMove(PCond.cycle(1), PMultiSkill.join(PMove.gain(2, PCLPowerData.Blur), PMove.selfExhaust()));
    }
}

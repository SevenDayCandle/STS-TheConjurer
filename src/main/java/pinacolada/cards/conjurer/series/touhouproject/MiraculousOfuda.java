package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class MiraculousOfuda extends PCLCard {
    public static final PCLCardData DATA = register(MiraculousOfuda.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON)
            .setAffinities(PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public MiraculousOfuda() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.gain(4, PCLPowerData.Warding).setUpgrade(2));
        addUseMove(PCond.exhaust(1), PMove.modifyCost(-1));
    }
}

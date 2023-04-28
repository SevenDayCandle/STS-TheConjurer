package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class BestialVitality extends PCLCard {
    public static final PCLCardData DATA = register(BestialVitality.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON)
            .setBlock(12, 3)
            .setAffinities(PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public BestialVitality() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
        addUseMove(PMove.gain(1, PCLElementHelper.Petra, PCLPowerHelper.DrawMinus));
    }
}

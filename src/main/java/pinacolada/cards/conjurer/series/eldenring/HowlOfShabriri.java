package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

@VisibleCard
public class HowlOfShabriri extends PCLCard {
    public static final PCLCardData DATA = register(HowlOfShabriri.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.UNCOMMON, PCLCardTarget.Self)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public HowlOfShabriri() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMultiSkill.join(PMove.applyToEveryone(3, PCLPowerData.Vigor),
                PMove.applyToEnemies(2, PCLPowerData.Bruised).setUpgrade(1)));
    }
}

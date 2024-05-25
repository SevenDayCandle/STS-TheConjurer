package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.conjurer.VentusPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

@VisibleCard
public class Stormcaller extends PCLCard {
    public static final PCLCardData DATA = register(Stormcaller.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON)
            .setAffinities(2, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public Stormcaller() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMod.discardPer(0).edit(f -> f.setForced(true)), PMultiSkill.join(PMove.gainTemporary(2, PCLPowerData.Thorns).setUpgrade(1), PMove.applyToEnemies(1, VentusPower.DATA)));
    }
}

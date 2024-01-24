package pinacolada.cards.conjurer.series.eldenring;


import basemod.abstracts.CustomPlayer;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class FlameGrantMeStrength extends PCLCard {
    public static final PCLCardData DATA = register(FlameGrantMeStrength.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Self)
            .setCostUpgrades(-1)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public FlameGrantMeStrength() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMod.perPowerAny(1, IgnisPower.DATA), PMove.gainTemporary(1, PCLPowerData.Strength));
        addUseMove(PMove.gain(2, IgnisPower.DATA));
    }
}

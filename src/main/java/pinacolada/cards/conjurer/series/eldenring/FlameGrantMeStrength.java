package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMod;
import pinacolada.skills.PMove;

@VisibleCard
public class FlameGrantMeStrength extends PCLCard {
    public static final PCLCardData DATA = register(FlameGrantMeStrength.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Self)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public FlameGrantMeStrength() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.gain(5, PCLElementHelper.Ignis));
        addUseMove(CMod.bonusOnReact(2), PMove.gainTemporary(3, PCLPowerHelper.Strength).setUpgrade(2));
    }
}

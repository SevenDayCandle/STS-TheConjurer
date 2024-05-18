package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class Determination extends PCLCard {
    public static final PCLCardData DATA = register(Determination.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON, PCLCardTarget.Self)
            .setCostUpgrades(-1)
            .setAffinities(PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public Determination() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.retain(2), PMove.modifyBlock(5).edit(f -> f.setOr(true).setForced(true)).useParent(true));
    }
}

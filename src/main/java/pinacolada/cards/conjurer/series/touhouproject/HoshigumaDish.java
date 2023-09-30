package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class HoshigumaDish extends PCLCard {
    public static final PCLCardData DATA = register(HoshigumaDish.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.SingleAlly)
            .setCostUpgrades(-1)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public HoshigumaDish() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.withdrawAlly(PCLCardTarget.SingleAlly, 2), PMove.reshuffle(1).useParentForce(), PMove.modifyDamage(2).useParentForce());
    }
}

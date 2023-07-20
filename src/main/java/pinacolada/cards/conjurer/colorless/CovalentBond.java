package pinacolada.cards.conjurer.colorless;

import com.megacrit.cardcrawl.cards.AbstractCard;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class CovalentBond extends PCLCard {
    public static final PCLCardData DATA = register(CovalentBond.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE, PCLCardTarget.None)
            .setCostUpgrades(-1)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Yellow, PCLAffinity.Silver)
            .setColorless();

    public CovalentBond() {
        super(DATA);
    }

    public void action(PSpecialSkill move, PCLUseInfo info, PCLActions order) {
        order.selectFromPile(getName(), move.amount, player.hand)
                .addCallback((cards) -> {
                    if (cards.size() > 0) {
                        Polymerization result = new Polymerization();
                        for (AbstractCard c : cards) {
                            player.hand.removeCard(c);
                            c.resetAttributes();
                            GameUtilities.resetVisualProperties(c);
                            result.addInheritedCard(c);
                        }

                        order.makeCardInHand(result).addCallback(c -> {
                            GameUtilities.modifyCostForCombat(result, -move.extra, true);
                        });
                    }
                });
    }

    @Override
    public void setup(Object input) {
        addSpecialMove(0, this::action, 2, 1);
    }
}
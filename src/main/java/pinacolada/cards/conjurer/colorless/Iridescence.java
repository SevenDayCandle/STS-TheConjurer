package pinacolada.cards.conjurer.colorless;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardSelection;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class Iridescence extends PCLCard {
    public static final PCLCardData DATA = register(Iridescence.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.SPECIAL, PCLCardTarget.None)
            .setAffinities(PCLAffinity.Star)
            .setTags(PCLCardTag.Exhaust)
            .setColorless();

    public Iridescence() {
        super(DATA);
    }

    public void action(PSpecialSkill move, PCLUseInfo info, PCLActions order) {
        order.selectFromPile(name, AbstractDungeon.player.hand.size(), AbstractDungeon.player.hand)
                .setOrigin(PCLCardSelection.Random)
                .addCallback(cards -> {
                    for (AbstractCard card : cards) {
                        if (card.costForTurn >= 0) {
                            int newCost = MathUtils.random(0, 3);
                            GameUtilities.modifyCostForCombat(card, newCost, false);
                            card.flash();
                        }
                    }
                });
    }

    @Override
    public void setup(Object input) {
        addUseMove(PMove.draw(2).setUpgrade(1));
        addSpecialMove(0, this::action, 1);
    }
}
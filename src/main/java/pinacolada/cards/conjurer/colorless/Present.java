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
public class Present extends PCLCard {
    public static final PCLCardData DATA = register(Present.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE, PCLCardTarget.Single)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Star)
            .setCostUpgrades(-1)
            .setColorless();

    public Present() {
        super(DATA);
    }

    @Override
    public void setup(Object input) {
        addSpecialMove(0, this::action, 1);
    }

    public void action(PSpecialSkill move, PCLUseInfo info, PCLActions order) {
        AbstractCard random = GameUtilities.getRandomCombatCard();
        if (random != null) {
            order.playCopy(random, info.target)
                    .addCallback(() -> PCLActions.bottom.makeCardInDiscardPile(random.makeStatEquivalentCopy()));
        }
    }
}
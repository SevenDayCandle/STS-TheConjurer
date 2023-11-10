package pinacolada.cards.conjurer.colorless;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;
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

@VisibleCard
public class ChemicalSynthesis extends PCLCard {
    public static final PCLCardData DATA = register(ChemicalSynthesis.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Single)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Silver)
            .setTags(PCLCardTag.Purge)
            .setCostUpgrades(-1)
            .setMaxCopies(1)
            .setColorless();

    public ChemicalSynthesis() {
        super(DATA);
    }

    public void action(PSpecialSkill move, PCLUseInfo info, PCLActions order) {
        for (AbstractPotion potion : AbstractDungeon.player.potions) {
            if (potion != null && potion.canUse()) {
                order.usePotion(potion, info.target, move.amount);
                return;
            }
        }
        order.heal(move.extra);
    }

    @Override
    public void setup(Object input) {
        addSpecialMove(0, this::action, 2, 4);
    }
}
package pinacolada.cards.conjurer.colorless;


import com.megacrit.cardcrawl.cards.AbstractCard;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.dungeon.CombatManager;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class MonsterReborn extends PCLCard {
    public static final PCLCardData DATA = register(MonsterReborn.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.RARE, PCLCardTarget.AllAlly)
            .setTags(PCLCardTag.Purge)
            .setAffinities(2, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.yuGiOh, true);

    public MonsterReborn() {
        super(DATA);
    }

    public void action(PSpecialSkill move, PCLUseInfo info, PCLActions order) {
        order.playFromPile(move.getName(), move.amount, move.target.getTarget(info), CombatManager.PURGED_CARDS)
                .setFilter(c -> c.type == PCLEnum.CardType.SUMMON && c instanceof PCLCard)
                .addCallback(cards -> {
                    for (AbstractCard c : cards) {
                        if (c.type == PCLEnum.CardType.SUMMON && c instanceof PCLCard) {
                            GameUtilities.modifyDamage(c, c.baseDamage * move.extra / 100, false, false);
                            ((PCLCard) c).updateHeal(c.baseHeal + c.baseHeal * move.extra / 100);
                        }
                    }
                });
    }

    public void setup(Object input) {
        addSpecialMove(0, this::action, 1, 30).setUpgradeExtra(20);
    }
}

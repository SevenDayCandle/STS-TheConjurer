package pinacolada.cards.conjurer.colorless;


import com.megacrit.cardcrawl.cards.AbstractCard;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.misc.CombatManager;
import pinacolada.misc.PCLUseInfo;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class MonsterReborn extends PCLCard
{
    public static final PCLCardData DATA = register(MonsterReborn.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.UNCOMMON, PCLCardTarget.AllAlly)
            .setTags(PCLCardTag.Purge)
            .setAffinities(2, PCLAffinity.Yellow)
            .setColorless();

    public MonsterReborn()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addSpecialMove(0, this::action, 1, 30).setUpgradeExtra(20);
    }

    public void action(PSpecialSkill move, PCLUseInfo info)
    {
        PCLActions.bottom.playFromPile(move.getName(), move.amount, move.target.getTarget(info.target), CombatManager.PURGED_CARDS)
                .setFilter(c -> c.type == PCLEnum.CardType.SUMMON && c instanceof PCLCard)
                .addCallback(cards -> {
                    for (AbstractCard c : cards)
                    {
                        if (c.type == PCLEnum.CardType.SUMMON && c instanceof PCLCard)
                        {
                            GameUtilities.modifySecondaryValueRelative((PCLCard) c, c.baseHeal * move.extra / 100, false);
                        }
                    }
                });
    }
}

package pinacolada.cards.conjurer.colorless;


import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import extendedui.EUIUtils;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardSelection;
import pinacolada.effects.AttackEffects;
import pinacolada.misc.PCLUseInfo;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.skills.skills.PTrigger;
import pinacolada.utilities.RandomizedList;

@VisibleCard
public class TheDefect extends PCLCard
{
    public static final PCLCardData DATA = register(TheDefect.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON)
            .setDamage(4, 2)
            .setPriority(1)
            .setHp(6, 1)
            .setAffinities(PCLAffinity.Blue)
            .setColorless();

    public TheDefect()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.LIGHTNING);
        addGainPower(PTrigger.interactable(PCond.payEnergy(2), getSpecialMove(0, this::specialMove, 1, 3)));
    }

    public void specialMove(PSpecialSkill move, PCLUseInfo info)
    {
        final RandomizedList<AbstractCard> choices = new RandomizedList<>(
          EUIUtils.filter(CardLibrary.getCardList(CardLibrary.LibraryType.BLUE), c -> c.rarity != CardRarity.SPECIAL && c.type != CardType.STATUS)
        );
        final CardGroup choice = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        final int limit = Math.max(move.extra, move.amount);
        while (choice.size() < limit)
        {
            AbstractCard c = choices.retrieve(rng);
            if (c != null)
            {
                choice.addToBottom(c.makeCopy());
            }
        }
        boolean automatic = move.extra <= move.amount;
        PCLActions.bottom.selectFromPile(getName(), move.amount, choice)
                .setOptions((automatic ? PCLCardSelection.Random : PCLCardSelection.Manual).toSelection(), automatic)
                .addCallback(cards -> {
                    for (AbstractCard c : cards)
                    {
                        c.costForTurn = 0;
                        PCLActions.bottom.makeCard(c, player.hand);
                    }
                });
    }
}

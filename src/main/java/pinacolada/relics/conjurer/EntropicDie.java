package pinacolada.relics.conjurer;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.RestRoom;
import extendedui.EUIUtils;
import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.PCLCard;
import pinacolada.interfaces.providers.CardRewardActionProvider;
import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.relics.pcl.GenericDice;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.GameUtilities;

@VisibleRelic
public class EntropicDie extends PCLRelic implements CardRewardActionProvider {
    public static final PCLRelicData DATA = register(EntropicDie.class, ConjurerResources.conjurer)
            .setProps(RelicTier.SHOP, LandingSound.SOLID)
            .setUnique(true);
    public static final int BONUS_PER_CARDS = 25;
    protected int rerolls;

    public EntropicDie() {
        super(DATA);
    }

    public boolean canAct() {
        return counter > 0;
    }

    public AbstractCard doAction(AbstractCard card, RewardItem rewardItem, int cardIndex) {
        setCounter(counter - 1);
        return getReward(card, rewardItem);
    }

    @Override
    public PCLRelicData[] getReplacementIDs() {
        return EUIUtils.array(GenericDice.DATA);
    }

    protected int getBonus() {
        return GameUtilities.getTotalCardsInRewardPool() / BONUS_PER_CARDS;
    }

    protected AbstractCard.CardRarity getRarity(AbstractCard card) {
        int roll = rng.random(100);
        if (roll < 2) {
            return null; // Colorless
        }
        if (roll < 4) {
            return AbstractCard.CardRarity.RARE;
        }
        if (roll < 12) {
            return card.rarity == AbstractCard.CardRarity.RARE ? AbstractCard.CardRarity.RARE : AbstractCard.CardRarity.UNCOMMON;
        }
        if (roll < 27) {
            return AbstractCard.CardRarity.UNCOMMON;
        }
        return AbstractCard.CardRarity.COMMON;
    }

    public AbstractCard getReward(AbstractCard card, RewardItem rewardItem) {
        AbstractCard reward = PGR.dungeon.getRandomRewardReplacementCard(getRarity(card), rewardItem.cards, AbstractDungeon.cardRng, true);
        if (reward instanceof PCLCard) {
            int factor = 2 + rerolls / 7;
            int change = MathUtils.random(0, factor) - factor / 2;
            GameUtilities.modifyDamage(reward, reward.baseDamage + change, false, false);
            ((PCLCard) reward).auxiliaryData.modifiedDamage = change;
            change = MathUtils.random(0, factor) - factor / 2;
            GameUtilities.modifyBlock(reward, reward.baseBlock + change, false, false);
            ((PCLCard) reward).auxiliaryData.modifiedBlock = change;
            if (reward.type == PCLEnum.CardType.SUMMON) {
                change = MathUtils.random(0, factor) - factor / 2;
                ((PCLCard) reward).updateHeal(reward.baseHeal + change);
                ((PCLCard) reward).auxiliaryData.modifiedHeal = change;
            }

            if (GameUtilities.chance(5 + rerolls)) {
                int costChange = MathUtils.random(-1, 1);

            }

        }
        rerolls += 1;
        return reward;
    }

    @Override
    public String getDescriptionImpl() {
        return formatDescription(0, BONUS_PER_CARDS);
    }

    @Override
    public void onEquip() {
        super.onEquip();

        setCounter(0);
    }

    @Override
    public void onEnterRoom(AbstractRoom room) {
        super.onEnterRoom(room);
        rerolls = 0;

        if (room instanceof RestRoom) {
            setCounter(counter + getBonus());
            flash();
        }
    }
}
package pinacolada.blights.conjurer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import extendedui.EUIUtils;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleBlight;
import pinacolada.blights.PCLBlight;
import pinacolada.blights.PCLBlightData;
import pinacolada.cards.base.PCLCard;
import pinacolada.dungeon.CombatManager;
import pinacolada.effects.PCLEffects;
import pinacolada.interfaces.subscribers.OnAllyDeathSubscriber;
import pinacolada.interfaces.subscribers.OnAllyWithdrawSubscriber;
import pinacolada.interfaces.subscribers.OnCardCreatedSubscriber;
import pinacolada.monsters.PCLCardAlly;
import pinacolada.relics.PCLRelicData;
import pinacolada.relics.conjurer.AeonianButterfly;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.GameUtilities;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

@VisibleBlight
public class Genesis extends PCLBlight implements OnAllyDeathSubscriber, OnAllyWithdrawSubscriber, OnCardCreatedSubscriber {
    public static final PCLBlightData DATA = register(Genesis.class, ConjurerResources.conjurer)
            .setUnique(true);

    public Genesis() {
        super(DATA);
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();
        subscribeToAll();
        CombatManager.controlPile.energy += player.energy.energyMaster;
        for (AbstractCard c : player.drawPile.group) {
            if (c.type == PCLEnum.CardType.SUMMON) {
                CombatManager.controlPile.add(c);
            }
        }
        for (AbstractCard c : player.hand.group) {
            if (c.type == PCLEnum.CardType.SUMMON) {
                CombatManager.controlPile.add(c);
            }
        }
        for (AbstractCard c : player.discardPile.group) {
            if (c.type == PCLEnum.CardType.SUMMON) {
                CombatManager.controlPile.add(c);
            }
        }
        player.drawPile.group.removeIf(c -> c.type == PCLEnum.CardType.SUMMON);
        player.hand.group.removeIf(c -> c.type == PCLEnum.CardType.SUMMON);
        player.discardPile.group.removeIf(c -> c.type == PCLEnum.CardType.SUMMON);
    }

    @Override
    public void onAllyDeath(PCLCard card, PCLCardAlly ally) {
        AbstractCard deckInstance = GameUtilities.getMasterDeckInstance(card.uuid);
        if (deckInstance != null) {
            PCLEffects.TopLevelQueue.showCardBriefly(deckInstance);
            AbstractDungeon.player.masterDeck.removeCard(deckInstance);
        }
    }

    @Override
    public void onAllyWithdraw(PCLCard card, PCLCardAlly ally, boolean triggerEffects) {
        CombatManager.controlPile.energy += card.energyOnUse;
    }

    @Override
    public void onCardCreated(AbstractCard c, boolean battleStart) {
        if (c.type == PCLEnum.CardType.SUMMON) {
            CardGroup cg = GameUtilities.findCardGroup(c);
            if (cg != null) {
                cg.removeCard(c);
            }
            CombatManager.controlPile.add(c);
        }
    }

    @Override
    public void onEquip() {
        super.onEquip();
        subscribeToAll();
    }
}

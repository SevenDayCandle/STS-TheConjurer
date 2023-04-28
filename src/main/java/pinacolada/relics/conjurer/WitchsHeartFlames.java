package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.conjurer.status.Status_SearingBurn;
import pinacolada.dungeon.CombatManager;
import pinacolada.effects.PCLEffects;
import pinacolada.interfaces.subscribers.OnCardCreatedSubscriber;
import pinacolada.relics.PCLRelic;
import pinacolada.resources.conjurer.ConjurerResources;

@VisibleRelic
public class WitchsHeartFlames extends PCLRelic implements OnCardCreatedSubscriber {
    public static final String ID = createFullID(ConjurerResources.conjurer, WitchsHeartFlames.class);

    public WitchsHeartFlames() {
        super(ID, RelicTier.UNCOMMON, LandingSound.MAGICAL, ConjurerResources.conjurer.playerClass);
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();

        CombatManager.subscribe(this);
    }

    @Override
    public void onCardCreated(AbstractCard card, boolean startOfBattle) {
        if (card.type == AbstractCard.CardType.CURSE || card.type == AbstractCard.CardType.STATUS) {
            PCLActions.last.replaceCard(card.uuid, new Status_SearingBurn());
            flash();
        }
    }

    @Override
    public void onEquip() {
        super.onEquip();

        for (AbstractCard c : player.masterDeck.group) {
            if (c.type == AbstractCard.CardType.CURSE || c.type == AbstractCard.CardType.STATUS) {
                player.masterDeck.group.remove(c);
                PCLEffects.TopLevelQueue.showAndObtain(new Status_SearingBurn(), Settings.WIDTH / 2f, Settings.HEIGHT / 2f, false);
            }
        }
    }
}
package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.pcl.curse.Curse_SearingBurn;
import pinacolada.effects.PCLEffects;
import pinacolada.interfaces.subscribers.OnCardCreatedSubscriber;
import pinacolada.misc.CombatManager;
import pinacolada.powers.common.BlastedPower;
import pinacolada.relics.PCLRelic;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.ui.combat.ConjurerReactionMeter;

@VisibleRelic
public class WitchsHeartFlames extends PCLRelic implements OnCardCreatedSubscriber
{
    public static final String ID = createFullID(ConjurerResources.conjurer, WitchsHeartFlames.class);
    public static final int BURNING_ATTACK_BONUS = 15;

    public WitchsHeartFlames()
    {
        super(ID, RelicTier.RARE, LandingSound.MAGICAL, ConjurerResources.conjurer.playerClass);
    }

    @Override
    public void onCardCreated(AbstractCard card, boolean startOfBattle)
    {
        if (card.type == AbstractCard.CardType.CURSE || card.type == AbstractCard.CardType.STATUS)
        {
            PCLActions.last.replaceCard(card.uuid, new Curse_SearingBurn());
            flash();
        }
    }

    @Override
    public void atBattleStart()
    {
        super.atBattleStart();

        CombatManager.subscribe(this);
        PCLActions.bottom.callback(() -> {
            ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Red).addAdditionalPower(BlastedPower.POWER_ID);
        });
    }

    @Override
    public void onEquip()
    {
        super.onEquip();

        for (AbstractCard c : player.masterDeck.group)
        {
            if (c.type == AbstractCard.CardType.CURSE || c.type == AbstractCard.CardType.STATUS)
            {
                player.masterDeck.group.remove(c);
                PCLEffects.TopLevelQueue.showAndObtain(new Curse_SearingBurn(), Settings.WIDTH / 2f, Settings.HEIGHT / 2f, false);
            }
        }
    }
}
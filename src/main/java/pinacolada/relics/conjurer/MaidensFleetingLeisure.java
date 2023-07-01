package pinacolada.relics.conjurer;

import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.PCLCard;
import pinacolada.dungeon.CombatManager;
import pinacolada.interfaces.subscribers.OnAllyWithdrawSubscriber;
import pinacolada.monsters.PCLCardAlly;
import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.GameUtilities;

@VisibleRelic
public class MaidensFleetingLeisure extends PCLRelic implements OnAllyWithdrawSubscriber {
    public static final PCLRelicData DATA = register(MaidensFleetingLeisure.class, ConjurerResources.conjurer)
            .setProps(RelicTier.UNCOMMON, LandingSound.MAGICAL);

    public MaidensFleetingLeisure() {
        super(DATA);
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();

        CombatManager.subscribe(this);
    }

    @Override
    public int getValue() {
        return 1;
    }

    @Override
    public void onAllyWithdraw(PCLCard pclCard, PCLCardAlly pclCardAlly) {
        PCLActions.bottom.callback(() -> {
            pclCard.updateHeal(pclCard.baseHeal + getValue());
            GameUtilities.modifyDamage(pclCard, pclCard.baseDamage + getValue(), false, false);
        });
    }
}
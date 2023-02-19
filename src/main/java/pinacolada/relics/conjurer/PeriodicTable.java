package pinacolada.relics.conjurer;

import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardAffinities;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.interfaces.subscribers.OnAllySummonSubscriber;
import pinacolada.interfaces.subscribers.OnAllyWithdrawSubscriber;
import pinacolada.misc.CombatManager;
import pinacolada.monsters.PCLCardAlly;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.relics.PCLRelic;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.GameUtilities;

import java.util.Arrays;
import java.util.HashSet;

@VisibleRelic
public class PeriodicTable extends PCLRelic implements OnAllySummonSubscriber, OnAllyWithdrawSubscriber
{
    public static final String ID = createFullID(ConjurerResources.conjurer, PeriodicTable.class);

    public PeriodicTable()
    {
        super(ID, RelicTier.STARTER, LandingSound.SOLID);
    }

    @Override
    public void atBattleStart()
    {
        CombatManager.subscribe(OnAllySummonSubscriber.class, this);
        CombatManager.subscribe(OnAllyWithdrawSubscriber.class, this);
    }

    @Override
    public void onAllySummon(PCLCard card, PCLCardAlly ally)
    {
        doAction(card, ally);
    }

    @Override
    public void onAllyWithdraw(PCLCard card, PCLCardAlly ally)
    {
        doAction(card, ally);
    }

    public void doAction(PCLCard card, PCLCardAlly ally)
    {
        HashSet<PCLAffinity> available = new HashSet<>(Arrays.asList(PCLAffinity.getAvailableAffinities()));
        PCLCardAffinities cardAffinities = GameUtilities.getPCLCardAffinities(card);
        if (cardAffinities != null)
        {
            for (PCLAffinity aff : cardAffinities.getAffinities(false))
            {
                if (available.contains(aff))
                {
                    PCLElementHelper debuff = PCLElementHelper.get(aff);
                    if (debuff != null)
                    {
                        PCLActions.delayed.applyPower(ally, PCLCardTarget.Single, debuff, getValue());
                        PCLActions.delayed.applyPower(PCLCardTarget.RandomEnemy, debuff, getValue());
                    }
                }
            }
            flash();
        }
    }

    public int getValue() {
        return 1;
    }
}
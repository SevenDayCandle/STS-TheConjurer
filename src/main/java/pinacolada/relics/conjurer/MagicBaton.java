package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.AffinityReactions;
import pinacolada.interfaces.subscribers.OnElementReactSubscriber;
import pinacolada.misc.CombatManager;
import pinacolada.powers.conjurer.BatonPassPower;
import pinacolada.relics.PCLRelic;
import pinacolada.resources.conjurer.ConjurerResources;

public class MagicBaton extends PCLRelic implements OnElementReactSubscriber
{
    public static final String ID = createFullID(ConjurerResources.conjurer, MagicBaton.class);

    public MagicBaton()
    {
        super(ID, RelicTier.STARTER, LandingSound.MAGICAL, ConjurerResources.conjurer.playerClass);
    }

    @Override
    public void onElementReact(AffinityReactions reactions, AbstractCreature m)
    {
        if (reactions.hasRedox())
        {
            PCLActions.bottom.applyPower(new BatonPassPower(player, 1));
            flash();
        }
    }

    @Override
    public void atBattleStart()
    {
        super.atBattleStart();

        CombatManager.onElementReact.subscribe(this);
    }
}
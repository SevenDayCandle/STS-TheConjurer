package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Frost;
import pinacolada.actions.PCLActions;
import pinacolada.interfaces.subscribers.OnTryChannelOrbSubscriber;
import pinacolada.misc.CombatManager;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.orbs.pcl.Water;
import pinacolada.relics.PCLStarterRelic;
import pinacolada.resources.conjurer.ConjurerResources;

public class PeriodicTable extends PCLStarterRelic implements OnTryChannelOrbSubscriber
{
    public static final String ID = createFullID(ConjurerResources.conjurer, PeriodicTable.class);

    public PeriodicTable()
    {
        super(ID, RelicTier.STARTER, LandingSound.MAGICAL, ConjurerResources.conjurer.playerClass);
    }

    @Override
    public AbstractOrb onTryChannelOrb(AbstractOrb orb)
    {
        return orb instanceof Frost ? new Water() : orb;
    }

    @Override
    protected void activateBattleEffect()
    {
        CombatManager.onTryChannelOrb.subscribe(this);
    }

    @Override
    public void atBattleStart()
    {
        super.atBattleStart();

        PCLActions.bottom.channelOrbs(augment != null ? PCLOrbHelper.get(augment.data.affinity) : PCLOrbHelper.randomElementalHelper(), 1);
    }

    @Override
    public String getUpdatedDescription()
    {
        return formatDescription(augment == null ? 0 : 1, augment != null ? PCLOrbHelper.get(augment.data.affinity).getTooltip() : "");
    }
}
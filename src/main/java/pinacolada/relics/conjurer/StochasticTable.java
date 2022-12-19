package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Frost;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCardData;
import pinacolada.interfaces.subscribers.OnTryChannelOrbSubscriber;
import pinacolada.misc.CombatManager;
import pinacolada.orbs.pcl.Water;
import pinacolada.relics.PCLStarterRelic;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.GameUtilities;

public class StochasticTable extends PCLStarterRelic implements OnTryChannelOrbSubscriber
{
    public static final String ID = createFullID(ConjurerResources.conjurer, StochasticTable.class);

    public StochasticTable()
    {
        super(ID, RelicTier.STARTER, LandingSound.MAGICAL, ConjurerResources.conjurer.playerClass, PeriodicTable.class);
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
    public String getUpdatedDescription()
    {
        return formatDescription(augment == null ? 0 : 1, augment != null ? getOrbCore(augment.data.affinity).strings.NAME : "");
    }

    @Override
    public void atBattleStart()
    {
        super.atBattleStart();

        PCLActions.bottom.playCopy(getOrbCore(augment != null ? augment.data.affinity : GameUtilities.getRandomElement(PCLAffinity.getAvailableAffinities())).makeCopy(false), null);
    }

    protected PCLCardData getOrbCore(PCLAffinity affinity) {
        switch (affinity) {
            case Red:
                return OrbCore_Fire.DATA;
            case Green:
                return OrbCore_Air.DATA;
            case Blue:
                return OrbCore_Water.DATA;
            case Orange:
                return OrbCore_Earth.DATA;
        }
        return null;
    }
}
package pinacolada.relics.conjurer;

import pinacolada.actions.PCLActions;
import pinacolada.powers.common.InnovationPower;
import pinacolada.relics.PCLRelic;

public class SpiritPoop3 extends PCLRelic
{
    public static final String ID = createFullID(SpiritPoop3.class);

    public SpiritPoop3()
    {
        super(ID, RelicTier.SPECIAL, LandingSound.SOLID);
    }

    @Override
    public void atBattleStart()
    {
        super.atBattleStart();
        PCLActions.bottom.applyPower(new InnovationPower(player, 2));
    }
}
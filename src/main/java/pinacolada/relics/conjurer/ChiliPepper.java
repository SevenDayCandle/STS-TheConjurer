package pinacolada.relics.conjurer;

import pinacolada.powers.PCLPowerHelper;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

public class ChiliPepper extends PCLPointerRelic
{
    public static final String ID = createFullID(ChiliPepper.class);
    public static final int AMOUNT = 3;

    public ChiliPepper()
    {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    public void setup()
    {
        addUseMove(PCond.onTurnStart(), PMultiSkill.join(PMove.gain(3, PCLPowerHelper.Vigor), PMove.applyToEveryone(1, PCLPowerHelper.Blasted)));
    }
}
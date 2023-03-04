package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

@VisibleRelic
public class ChiliPepper extends PCLPointerRelic
{
    public static final String ID = createFullID(ChiliPepper.class);

    public ChiliPepper()
    {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    public void setup()
    {
        addUseMove(PCond.onTurnStart(), PMultiSkill.join(PMove.applyToEnemies(4, PCLElementHelper.Blasted)));
    }
}
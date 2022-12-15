package pinacolada.relics.conjurer;

import pinacolada.relics.PCLRelic;

public class SpiritPoop2 extends PCLRelic
{
    public static final String ID = createFullID(SpiritPoop2.class);

    public SpiritPoop2()
    {
        super(ID, RelicTier.SPECIAL, LandingSound.SOLID);
    }
}
package pinacolada.potions.conjurer;

import com.badlogic.gdx.graphics.Color;
import pinacolada.potions.PCLPotion;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

public class ChillingPotion extends PCLPotion
{
    public static final String POTION_ID = createFullID(ConjurerResources.conjurer, ChillingPotion.class);

    public ChillingPotion()
    {
        super(POTION_ID, PotionRarity.COMMON, PotionSize.M, PotionEffect.NONE, Color.SKY, Color.NAVY, Color.BLUE, ConjurerResources.conjurer.playerClass);
    }

    public void setup()
    {
        addUseMove(PMove.applyToSingle(getPotency(), PCLPowerHelper.Chilled));
    }

    @Override
    public int getPotency(int ascensionLevel)
    {
        return 6;
    }
}

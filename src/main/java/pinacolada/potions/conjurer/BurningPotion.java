package pinacolada.potions.conjurer;

import com.badlogic.gdx.graphics.Color;
import pinacolada.potions.PCLPotion;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

public class BurningPotion extends PCLPotion
{
    public static final String POTION_ID = createFullID(ConjurerResources.conjurer, BurningPotion.class);

    public BurningPotion()
    {
        super(POTION_ID, PotionRarity.COMMON, PotionSize.M, PotionEffect.NONE, Color.RED, Color.SCARLET, Color.FIREBRICK, ConjurerResources.conjurer.playerClass);
    }

    public void setup()
    {
        addUseMove(PMove.applyToEnemies(getPotency(), PCLElementHelper.Burning));
    }

    @Override
    public int getPotency(int ascensionLevel)
    {
        return 6;
    }
}

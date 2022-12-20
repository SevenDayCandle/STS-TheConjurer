package pinacolada.potions.conjurer;

import com.badlogic.gdx.graphics.Color;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.potions.PCLPotion;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;

public class BurningPotion extends PCLPotion
{
    public static final String POTION_ID = createFullID(ConjurerResources.conjurer, BurningPotion.class);

    public BurningPotion()
    {
        super(POTION_ID, PotionRarity.COMMON, PotionSize.M, PotionEffect.NONE, Color.RED, Color.SCARLET, Color.FIREBRICK, ConjurerResources.conjurer.playerClass);
    }

    public void setup()
    {
        addUseMove(CMove.applyElementToEnemies(getPotency(), PCLAffinity.Red));
    }

    @Override
    public int getPotency(int ascensionLevel)
    {
        return 6;
    }
}

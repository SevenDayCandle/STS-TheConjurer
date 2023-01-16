package pinacolada.potions.conjurer;

import com.badlogic.gdx.graphics.Color;
import pinacolada.potions.PCLPotion;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

public class FlowingPotion extends PCLPotion
{
    public static final String POTION_ID = createFullID(ConjurerResources.conjurer, FlowingPotion.class);

    public FlowingPotion()
    {
        super(POTION_ID, PotionRarity.COMMON, PotionSize.M, PotionEffect.NONE, Color.LIME, Color.FOREST, Color.GREEN, ConjurerResources.conjurer.playerClass);
    }

    public void setup()
    {
        addUseMove(PMove.applyToEnemies(getPotency(), PCLElementHelper.Aer));
    }

    @Override
    public int getPotency(int ascensionLevel)
    {
        return 6;
    }
}

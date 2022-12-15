package pinacolada.potions.conjurer;

import com.badlogic.gdx.graphics.Color;
import pinacolada.potions.PCLPotion;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

public class LeafPotion extends PCLPotion
{
    public static final String POTION_ID = createFullID(ConjurerResources.conjurer, LeafPotion.class);

    public LeafPotion()
    {
        super(POTION_ID, PotionRarity.UNCOMMON, PotionSize.H, PotionEffect.NONE, Color.FOREST, Color.LIME, Color.GREEN, ConjurerResources.conjurer.playerClass);
    }

    public void setup()
    {
        addUseMove(PMove.applyToSingle(getPotency(), PCLPowerHelper.Flowed, PCLPowerHelper.Poison));
    }

    @Override
    public int getPotency(int ascensionLevel)
    {
        return 4;
    }
}

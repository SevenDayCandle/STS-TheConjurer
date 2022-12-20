package pinacolada.potions.conjurer;

import com.badlogic.gdx.graphics.Color;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.potions.PCLPotion;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;

public class FlowingPotion extends PCLPotion
{
    public static final String POTION_ID = createFullID(ConjurerResources.conjurer, FlowingPotion.class);

    public FlowingPotion()
    {
        super(POTION_ID, PotionRarity.COMMON, PotionSize.M, PotionEffect.NONE, Color.LIME, Color.FOREST, Color.GREEN, ConjurerResources.conjurer.playerClass);
    }

    public void setup()
    {
        addUseMove(CMove.applyElementToEnemies(getPotency(), PCLAffinity.Green));
    }

    @Override
    public int getPotency(int ascensionLevel)
    {
        return 6;
    }
}

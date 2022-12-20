package pinacolada.potions.conjurer;

import com.badlogic.gdx.graphics.Color;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.potions.PCLPotion;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;

public class ChillingPotion extends PCLPotion
{
    public static final String POTION_ID = createFullID(ConjurerResources.conjurer, ChillingPotion.class);

    public ChillingPotion()
    {
        super(POTION_ID, PotionRarity.COMMON, PotionSize.M, PotionEffect.NONE, Color.SKY, Color.NAVY, Color.BLUE, ConjurerResources.conjurer.playerClass);
    }

    public void setup()
    {
        addUseMove(CMove.applyElementToEnemies(getPotency(), PCLAffinity.Blue));
    }

    @Override
    public int getPotency(int ascensionLevel)
    {
        return 6;
    }
}

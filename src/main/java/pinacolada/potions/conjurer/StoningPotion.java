package pinacolada.potions.conjurer;

import com.badlogic.gdx.graphics.Color;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.potions.PCLPotion;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;

public class StoningPotion extends PCLPotion
{
    public static final String POTION_ID = createFullID(ConjurerResources.conjurer, StoningPotion.class);

    public StoningPotion()
    {
        super(POTION_ID, PotionRarity.COMMON, PotionSize.M, PotionEffect.NONE, Color.ORANGE, Color.TAN, Color.BROWN, ConjurerResources.conjurer.playerClass);
    }

    public void setup()
    {
        addUseMove(CMove.applyElementToEnemies(getPotency(), PCLAffinity.Orange));
    }

    @Override
    public int getPotency(int ascensionLevel)
    {
        return 6;
    }
}

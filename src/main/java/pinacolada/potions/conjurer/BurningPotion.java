package pinacolada.potions.conjurer;

import com.badlogic.gdx.graphics.Color;
import pinacolada.annotations.VisiblePotion;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.potions.PCLPotion;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

@VisiblePotion
public class BurningPotion extends PCLPotion
{
    public static final String POTION_ID = createFullID(ConjurerResources.conjurer, BurningPotion.class);

    public BurningPotion()
    {
        super(POTION_ID, PotionRarity.COMMON, PotionSize.M, PotionEffect.NONE, Color.RED, Color.SCARLET, Color.FIREBRICK, ConjurerResources.conjurer.playerClass);
    }

    public void setup()
    {
        addUseMove(PMultiSkill.join(PMove.applyToEnemies(getPotency(), PCLElementHelper.Ignis), PMove.stabilize(PCLCardTarget.AllEnemy, PCLElementHelper.Ignis)));
    }

    @Override
    public int getPotency(int ascensionLevel)
    {
        return 6;
    }
}

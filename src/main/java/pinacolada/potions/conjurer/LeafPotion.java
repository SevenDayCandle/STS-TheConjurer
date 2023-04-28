package pinacolada.potions.conjurer;

import com.badlogic.gdx.graphics.Color;
import pinacolada.annotations.VisiblePotion;
import pinacolada.potions.PCLPotion;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisiblePotion
public class LeafPotion extends PCLPotion {
    public static final String POTION_ID = createFullID(ConjurerResources.conjurer, LeafPotion.class);

    public LeafPotion() {
        super(POTION_ID, PotionRarity.UNCOMMON, PotionSize.H, PotionEffect.NONE, Color.FOREST, Color.LIME, Color.GREEN, ConjurerResources.conjurer.playerClass);
    }

    @Override
    public int getPotency(int ascensionLevel) {
        return 4;
    }

    public void setup() {
        addUseMove(PMove.applyToSingle(getPotency(), PCLElementHelper.Aer, PCLPowerHelper.Poison));
    }
}

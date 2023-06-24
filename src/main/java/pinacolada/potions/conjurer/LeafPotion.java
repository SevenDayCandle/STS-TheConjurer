package pinacolada.potions.conjurer;

import com.badlogic.gdx.graphics.Color;
import pinacolada.annotations.VisiblePotion;
import pinacolada.potions.PCLPotion;
import pinacolada.potions.PCLPotionData;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisiblePotion
public class LeafPotion extends PCLPotion {
    public static final PCLPotionData DATA = register(LeafPotion.class, ConjurerResources.conjurer)
            .setProps(PotionRarity.UNCOMMON, PotionSize.H)
            .setBottleColor(Color.FOREST, Color.LIME, Color.GREEN);

    public LeafPotion() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PMove.applyToSingle(4, PCLElementHelper.Ventus, PCLPowerHelper.Poison));
    }
}

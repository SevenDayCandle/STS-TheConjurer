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
public class ArborealPotion extends PCLPotion {
    public static final PCLPotionData DATA = register(ArborealPotion.class, ConjurerResources.conjurer)
            .setProps(PotionRarity.UNCOMMON, PotionSize.H)
            .setBottleColor(Color.FOREST, Color.LIME, Color.GREEN);

    public ArborealPotion() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PMove.applyToSingle(2, PCLElementHelper.Ventus, PCLElementHelper.Petra, PCLPowerHelper.Poison));
    }
}

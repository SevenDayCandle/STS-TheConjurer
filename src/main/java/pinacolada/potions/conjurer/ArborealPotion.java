package pinacolada.potions.conjurer;

import com.badlogic.gdx.graphics.Color;
import pinacolada.annotations.VisiblePotion;
import pinacolada.potions.PCLPotion;
import pinacolada.potions.PCLPotionData;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.conjurer.PetraPower;
import pinacolada.powers.conjurer.VentusPower;
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
        addUseMove(PMove.applyToSingle(2, VentusPower.DATA, PetraPower.DATA, PCLPowerData.Poison).setUpgrade(2));
    }
}

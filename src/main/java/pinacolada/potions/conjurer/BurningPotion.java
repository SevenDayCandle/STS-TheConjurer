package pinacolada.potions.conjurer;

import com.badlogic.gdx.graphics.Color;
import pinacolada.annotations.VisiblePotion;
import pinacolada.potions.PCLPotion;
import pinacolada.potions.PCLPotionData;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

@VisiblePotion
public class BurningPotion extends PCLPotion {
    public static final PCLPotionData DATA = register(BurningPotion.class, ConjurerResources.conjurer)
            .setProps(PotionRarity.COMMON, PotionSize.M)
            .setBottleColor(Color.RED, Color.SCARLET, Color.FIREBRICK);

    public BurningPotion() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PMultiSkill.join(PMove.applyToEnemies(2, IgnisPower.DATA).setUpgrade(2)));
    }
}

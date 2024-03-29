package pinacolada.potions.conjurer;

import com.badlogic.gdx.graphics.Color;
import pinacolada.annotations.VisiblePotion;
import pinacolada.potions.PCLPotion;
import pinacolada.potions.PCLPotionData;
import pinacolada.powers.conjurer.VentusPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

@VisiblePotion
public class FlowingPotion extends PCLPotion {
    public static final PCLPotionData DATA = register(FlowingPotion.class, ConjurerResources.conjurer)
            .setProps(PotionRarity.COMMON, PotionSize.M)
            .setBottleColor(Color.LIME, Color.FOREST, Color.GREEN);

    public FlowingPotion() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PMultiSkill.join(PMove.applyToEnemies(2, VentusPower.DATA).setUpgrade(2)));
    }
}

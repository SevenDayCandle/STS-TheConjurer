package pinacolada.potions.conjurer;

import com.badlogic.gdx.graphics.Color;
import pinacolada.annotations.VisiblePotion;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.potions.PCLPotion;
import pinacolada.potions.PCLPotionData;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

@VisiblePotion
public class StoningPotion extends PCLPotion {
    public static final PCLPotionData DATA = register(StoningPotion.class, ConjurerResources.conjurer)
            .setProps(PotionRarity.COMMON, PotionSize.M)
            .setBottleColor(Color.ORANGE, Color.TAN, Color.BROWN);

    public StoningPotion() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PMultiSkill.join(PMove.applyToEnemies(6, PCLElementHelper.Petra), PMove.stabilize(PCLCardTarget.All, PCLElementHelper.Petra)));
    }
}

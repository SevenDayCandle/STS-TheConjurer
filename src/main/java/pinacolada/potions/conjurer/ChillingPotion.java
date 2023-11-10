package pinacolada.potions.conjurer;

import com.badlogic.gdx.graphics.Color;
import pinacolada.annotations.VisiblePotion;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.potions.PCLPotion;
import pinacolada.potions.PCLPotionData;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

@VisiblePotion
public class ChillingPotion extends PCLPotion {
    public static final PCLPotionData DATA = register(ChillingPotion.class, ConjurerResources.conjurer)
            .setProps(PotionRarity.COMMON, PotionSize.M)
            .setBottleColor(Color.SKY, Color.NAVY, Color.BLUE);

    public ChillingPotion() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PMultiSkill.join(PMove.applyToEnemies(2, AquaPower.DATA).setUpgrade(2), CMove.stabilize(PCLCardTarget.All, AquaPower.DATA)));
    }
}

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
public class ChillingPotion extends PCLPotion {
    public static final String POTION_ID = createFullID(ConjurerResources.conjurer, ChillingPotion.class);

    public ChillingPotion() {
        super(POTION_ID, PotionRarity.COMMON, PotionSize.M, PotionEffect.NONE, Color.SKY, Color.NAVY, Color.BLUE, ConjurerResources.conjurer.playerClass);
    }

    @Override
    public int getPotency(int ascensionLevel) {
        return 6;
    }

    public void setup() {
        addUseMove(PMultiSkill.join(PMove.applyToEnemies(getPotency(), PCLElementHelper.Gelus), PMove.stabilize(PCLCardTarget.AllEnemy, PCLElementHelper.Gelus)));
    }
}

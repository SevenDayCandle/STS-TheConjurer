package pinacolada.cards.conjurer.basic;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.vfx.ScreenOnFireEffect3;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class BlazingHeat extends PCLCard {
    public static final PCLCardData DATA = register(BlazingHeat.class, ConjurerResources.conjurer)
            .setPower(3, CardRarity.RARE)
            .setAffinities(2, PCLAffinity.Red)
            .setCostUpgrades(-1)
            .setMaxCopies(1)
            .setCore();

    public BlazingHeat() {
        super(DATA);
    }

    public void setup(Object input) {
        addSpecialPower(0, BlazingHeatPower::new, 1);
    }

    public static class BlazingHeatPower extends PSpecialCardPower {
        public static final PCLPowerData PDATA = createFromCard(BlazingHeatPower.class, DATA);

        public BlazingHeatPower(AbstractCreature owner, AbstractCreature source, PSkill<?> move) {
            super(PDATA, owner, source, move);
        }

        @Override
        public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
            super.onApplyPower(power, target, source);
            if (power instanceof BlastedPower) {
                ((BlastedPower) power).expanded = true;
            }
        }

        public void onInitialApplication() {
            super.onInitialApplication();
            PCLActions.bottom.playVFX(new ScreenOnFireEffect3());
            PCLActions.bottom.callback(() -> {
                for (AbstractPower po : GameUtilities.getPowers(BlastedPower.DATA.ID)) {
                    if (po instanceof BlastedPower) {
                        ((BlastedPower) po).expanded = true;
                    }
                }
            });
        }
    }
}

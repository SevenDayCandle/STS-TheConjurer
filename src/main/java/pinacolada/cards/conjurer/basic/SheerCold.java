package pinacolada.cards.conjurer.basic;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.effects.vfx.ScreenFreezingEffect;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.powers.conjurer.CooledPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class SheerCold extends PCLCard {
    public static final PCLCardData DATA = register(SheerCold.class, ConjurerResources.conjurer)
            .setPower(3, CardRarity.RARE)
            .setAffinities(2, PCLAffinity.Blue)
            .setCostUpgrades(-1)
            .setMaxCopies(1)
            .setCore();

    public SheerCold() {
        super(DATA);
    }

    public void setup(Object input) {
        addSpecialPower(0, (s, i) -> new SheerColdPower(i.source, s), 25);
    }

    public static class SheerColdPower extends PSpecialCardPower {
        public SheerColdPower(AbstractCreature owner, PSkill<?> move) {
            super(SheerCold.DATA, owner, move);
        }

        @Override
        public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
            super.onApplyPower(power, target, source);
            if (power instanceof CooledPower) {
                ((CooledPower) power).expanded = true;
            }
        }

        @Override
        public void onInitialApplication() {
            super.onInitialApplication();

            PCLActions.bottom.playVFX(new ScreenFreezingEffect());
            PCLActions.bottom.callback(() -> {
                ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Blue).addAdditionalPower(CooledPower.POWER_ID);
                for (AbstractPower po : GameUtilities.getPowers(CooledPower.POWER_ID)) {
                    if (po instanceof CooledPower) {
                        ((CooledPower) po).expanded = true;
                    }
                }
            });
        }
    }
}

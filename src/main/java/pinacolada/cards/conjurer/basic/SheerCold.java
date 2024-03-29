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
import pinacolada.interfaces.subscribers.OnRemovePowerSubscriber;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.powers.conjurer.CooledPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;

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
        addSpecialPower(0, SheerColdPower::new);
    }

    public static class SheerColdPower extends PSpecialCardPower implements OnRemovePowerSubscriber {
        public static final PCLPowerData PDATA = createFromCard(SheerColdPower.class, DATA);

        public SheerColdPower(AbstractCreature owner, AbstractCreature source, PSkill<?> move) {
            super(PDATA, owner, source, move);
        }

        @Override
        public void onInitialApplication() {
            super.onInitialApplication();

            PCLActions.bottom.playVFX(new ScreenFreezingEffect());
            PCLActions.bottom.callback(() -> {
                ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Blue).addAdditionalPower(CooledPower.DATA.ID);
            });
        }

        @Override
        public void onRemovePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
            // Was removed at end of round naturally
            if (power instanceof CooledPower && ((CooledPower) power).turns <= 0) {
                PCLActions.delayed.applyPower(power.owner, CooledPower.DATA, power.amount);
            }
        }
    }
}

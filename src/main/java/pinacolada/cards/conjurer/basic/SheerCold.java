package pinacolada.cards.conjurer.basic;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.vfx.ScreenFreezingEffect;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.powers.conjurer.CooledPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.utilities.GameUtilities;

import java.util.ArrayList;

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
        addSpecialPower(0, (s, i) -> new SheerColdPower(i.source, i.source, s), 20);
    }

    public static class SheerColdPower extends PSpecialCardPower {
        public static final PCLPowerData PDATA = createFromCard(SheerColdPower.class, DATA);

        public SheerColdPower(AbstractCreature owner, AbstractCreature source, PSkill<?> move) {
            super(PDATA, owner, source, move);
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
                for (AbstractPower po : GameUtilities.getPowers(CooledPower.DATA.ID)) {
                    if (po instanceof CooledPower) {
                        ((CooledPower) po).expanded = true;
                    }
                }
            });
        }

        @Override
        public void atEndOfRound() {
            super.atEndOfRound();
            ArrayList<CooledPower> powers = GameUtilities.getPowers(CooledPower.class);
            int stacks = 0;
            for (CooledPower po : powers) {
                int apply = MathUtils.ceil(po.amount * amount / 100f);
                PCLActions.last.applyPower(po.owner, CooledPower.DATA, apply);
            }
        }
    }
}

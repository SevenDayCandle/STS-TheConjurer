package pinacolada.cards.conjurer.basic;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cardmods.PermanentBlockModifier;
import pinacolada.cardmods.PermanentDamageModifier;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.vfx.ScreenLeavesEffect;
import pinacolada.interfaces.subscribers.OnSpecificPowerActivatedSubscriber;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.powers.conjurer.FlowPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;

@VisibleCard
public class VerdantGale extends PCLCard {
    public static final PCLCardData DATA = register(VerdantGale.class, ConjurerResources.conjurer)
            .setPower(3, CardRarity.RARE)
            .setAffinities(2, PCLAffinity.Green)
            .setCostUpgrades(-1)
            .setMaxCopies(1)
            .setCore();

    public VerdantGale() {
        super(DATA);
    }

    public void setup(Object input) {
        addSpecialPower(0, (t, o, s) -> new VerdantGalePower(t, o, s), 3);
    }

    public static class VerdantGalePower extends PSpecialCardPower implements OnSpecificPowerActivatedSubscriber {
        public static final PCLPowerData PDATA = createFromCard(VerdantGalePower.class, DATA);

        public VerdantGalePower(AbstractCreature owner, AbstractCreature source, PSkill<?> move) {
            super(PDATA, owner, source, move);
        }

        @Override
        public void onInitialApplication() {
            super.onInitialApplication();

            PCLActions.bottom.playVFX(new ScreenLeavesEffect());
        }

        @Override
        public boolean onPowerActivated(AbstractPower power, AbstractCreature source, boolean originalValue) {
            if (power instanceof FlowPower && FlowPower.drawn != null) {
                PermanentDamageModifier.apply(FlowPower.drawn, amount);
                PermanentBlockModifier.apply(FlowPower.drawn, amount);
            }
            return originalValue;
        }
    }
}

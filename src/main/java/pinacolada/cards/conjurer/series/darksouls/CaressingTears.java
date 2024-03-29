package pinacolada.cards.conjurer.series.darksouls;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.ConjurerEFK;
import pinacolada.effects.PCLEffects;
import pinacolada.interfaces.subscribers.OnTryApplyPowerSubscriber;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.powers.conjurer.CooledPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;

@VisibleCard
public class CaressingTears extends PCLCard {
    public static final PCLCardData DATA = register(CaressingTears.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE, PCLCardTarget.All)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public CaressingTears() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.applyToEveryone(3, AquaPower.DATA));
        addSpecialPower(0, CaressingTearsPower::new, 2, 3).setUpgradeExtra(1);
    }

    public static class CaressingTearsPower extends PSpecialCardPower implements OnTryApplyPowerSubscriber {
        public static final PCLPowerData PDATA = createFromCard(CaressingTearsPower.class, DATA)
                .setEndTurnBehavior(PCLPowerData.Behavior.TurnBased);

        public CaressingTearsPower(AbstractCreature owner, AbstractCreature source, PSkill<?> move) {
            super(PDATA, owner, source, move);
        }

        @Override
        public void onInitialApplication() {
            super.onInitialApplication();
            PCLEffects.Queue.playEFX(ConjurerEFK.CURE01);
        }

        @Override
        public boolean tryApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source, AbstractGameAction action) {
            if (VulnerablePower.POWER_ID.equals(power.ID) || WeakPower.POWER_ID.equals(power.ID) || FrailPower.POWER_ID.equals(power.ID)) {
                PCLActions.bottom.gainTemporaryHP(move.extra);
                flash();
                return false;
            }
            return true;
        }
    }
}

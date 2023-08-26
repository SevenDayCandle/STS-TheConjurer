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
import pinacolada.powers.PSpecialCardPower;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;

@VisibleCard
public class CaressingTears extends PCLCard {
    public static final PCLCardData DATA = register(CaressingTears.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE, PCLCardTarget.Team)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public CaressingTears() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.gain(5, PCLElementHelper.Aqua));
        addSpecialPower(0, (s, i) -> new CaressingTearsPower(i.source, s), 1, 3).setUpgradeExtra(1);
    }

    public static class CaressingTearsPower extends PSpecialCardPower implements OnTryApplyPowerSubscriber {
        public CaressingTearsPower(AbstractCreature owner, PSkill<?> move) {
            super(DATA, owner, move);
            initialize(move.amount, PowerType.BUFF, true);
        }

        @Override
        public void atStartOfTurn() {
            super.atStartOfTurn();
            reducePower(1);
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

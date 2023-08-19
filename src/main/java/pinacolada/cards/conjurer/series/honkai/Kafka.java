package pinacolada.cards.conjurer.series.honkai;


import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.interfaces.subscribers.OnApplyPowerSubscriber;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PSkill;
import pinacolada.skills.delay.DelayTiming;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class Kafka extends PCLCard {
    public static final PCLCardData DATA = register(Kafka.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.RARE, PCLAttackType.Piercing, PCLCardTarget.AllEnemy, DelayTiming.EndOfTurnFirst)
            .setDamage(4, 1)
            .setHp(7, 2)
            .setAffinities(PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.honkai);

    public Kafka() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.DARKNESS).setBonus(PMod.perPowerAny(1, PCLPowerHelper.Weak, PCLPowerHelper.Vulnerable), 1);
        addSpecialPower(0, (s, i) -> new KafkaPower(i.source, s), 1, 1);
    }

    public static class KafkaPower extends PSpecialCardPower implements OnApplyPowerSubscriber {
        public KafkaPower(AbstractCreature owner, PSkill<?> move) {
            super(DATA, owner, move);
        }

        @Override
        public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
            super.onApplyPower(power, target, source);

            if (source != null && (VulnerablePower.POWER_ID.equals(power.ID) || WeakPower.POWER_ID.equals(power.ID))) {
                for (AbstractCreature creature : GameUtilities.getAllCharacters(true)) {
                    if (creature != target) {
                        PCLActions.bottom.applyPower(null, creature, power);
                    }
                }
            }

            flash();
        }
    }
}

package pinacolada.cards.conjurer.colorless;


import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.interfaces.markers.OutOfCombatMove;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.common.SorceryPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.skills.skills.PCustomCond;
import pinacolada.skills.skills.PMultiSkill;

@VisibleCard
public class PhoenixWright extends PCLCard {
    public static final PCLCardData DATA = register(PhoenixWright.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON)
            .setDamage(1, 1)
            .setHp(7, 2)
            .setAffinities(PCLAffinity.Orange, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.phoenixWright, true);

    public PhoenixWright() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.BLUNT_LIGHT);
        addUseMove(new PhoenixWrightCond(DATA, 3, 1));
    }

    protected static class PhoenixWrightCond extends PCustomCond implements OutOfCombatMove {
        protected PSkill<?> actualMove;
        protected AbstractMonster.Intent lastIntent;
        protected AbstractCreature lastTarget = null;

        public PhoenixWrightCond(PCustomCond other) {
            super(other);
        }

        public PhoenixWrightCond(PCLCardData data, int amount, int extra) {
            super(data, 0, amount, extra);
        }

        protected PSkill<?> getActualMove(AbstractMonster enemy) {
            if (enemy != null) {
                switch (enemy.intent) {
                    case ATTACK:
                        return PMove.gainBlockPlayer(amount);
                    case ATTACK_BUFF:
                        return PMultiSkill.join(
                                PMove.gainBlockPlayer(amount - extra),
                                PMove.gain(extra, PCLPowerData.Vigor)
                        );
                    case ATTACK_DEBUFF:
                        return PMultiSkill.join(
                                PMove.gainBlockPlayer(amount - extra),
                                PMove.applyToSingle(extra, PCLPowerData.Weak)
                        );
                    case ATTACK_DEFEND:
                        return PMultiSkill.join(
                                PMove.dealDamage(amount - extra),
                                PMove.gainBlockPlayer(amount - extra)
                        );
                    case BUFF:
                        return PMove.gain(amount, PCLPowerData.Vigor);
                    case DEBUFF:
                        return PMove.applyToSingle(extra, PCLPowerData.Weak, PCLPowerData.Vulnerable);
                    case STRONG_DEBUFF:
                        return PMove.applyToSingle(extra, PCLPowerData.Weak, PCLPowerData.Vulnerable, PCLPowerData.Blinded);
                    case DEFEND:
                        return PMove.dealDamage(amount + extra);
                    case DEFEND_DEBUFF:
                        return PMultiSkill.join(
                                PMove.dealDamage(amount),
                                PMove.applyToSingle(extra, PCLPowerData.Vulnerable)
                        );
                    case DEFEND_BUFF:
                        return PMultiSkill.join(
                                PMove.dealDamage(amount),
                                PMove.gain(extra, PCLPowerData.PlatedArmor)
                        );
                    case ESCAPE:
                        return PMultiSkill.join(
                                PMove.dealDamage(3 * amount)
                        );
                    case SLEEP:
                    case STUN:
                        return PMove.gainPlayer(extra, PCLPowerData.Energized);
                    case UNKNOWN:
                        return PMove.gainTempHP(extra);
                    case MAGIC:
                        return PMove.gain(extra, SorceryPower.DATA);
                    default:
                        return PMove.gain(extra, PCLPowerData.Malleable);
                }
            }
            return null;
        }

        @Override
        public String getText(PCLCardTarget perpsective, Object requestor, boolean addPeriod) {
            if (actualMove != null) {
                return actualMove.getText(perpsective, requestor, addPeriod);
            }
            return super.getText(perpsective, requestor, addPeriod);
        }

        @Override
        public void onDrag(AbstractMonster m) {
            super.onDrag(m);
            tryChangeEffect(m);

            if (actualMove != null) {
                actualMove.onDrag(m);
            }
        }

        @Override
        public void refresh(PCLUseInfo info, boolean conditionMet, boolean isUsing) {
            super.refresh(info, conditionMet, isUsing);
            tryChangeEffect(info.target);
        }

        protected void tryChangeEffect(AbstractCreature target) {
            if (lastTarget != target || (lastTarget instanceof AbstractMonster && ((AbstractMonster) lastTarget).intent != lastIntent)) {
                lastTarget = target;

                if (target instanceof AbstractMonster) {
                    lastIntent = ((AbstractMonster) target).intent;
                    actualMove = getActualMove((AbstractMonster) target);
                }
                else {
                    lastIntent = null;
                    actualMove = null;
                }

                sourceCard.initializeDescription();
            }
        }

        protected void useImpl(PCLUseInfo info, PCLActions order) {
            if (actualMove != null) {
                actualMove.use(info, order);
            }
        }
    }
}

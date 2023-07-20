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
import pinacolada.powers.PCLPowerHelper;
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
            .setDamage(2, 1)
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
                                PMove.gain(extra, PCLPowerHelper.Vigor)
                        );
                    case ATTACK_DEBUFF:
                        return PMultiSkill.join(
                                PMove.gainBlockPlayer(amount - extra),
                                PMove.applyToSingle(extra, PCLPowerHelper.Weak)
                        );
                    case ATTACK_DEFEND:
                        return PMultiSkill.join(
                                PMove.dealDamage(amount - extra),
                                PMove.gainBlockPlayer(amount - extra)
                        );
                    case BUFF:
                        return PMove.gain(amount, PCLPowerHelper.Vigor);
                    case DEBUFF:
                        return PMove.applyToSingle(extra, PCLPowerHelper.Weak, PCLPowerHelper.Vulnerable);
                    case STRONG_DEBUFF:
                        return PMove.applyToSingle(extra, PCLPowerHelper.Weak, PCLPowerHelper.Vulnerable, PCLPowerHelper.Blinded);
                    case DEFEND:
                        return PMove.dealDamage(amount + extra);
                    case DEFEND_DEBUFF:
                        return PMultiSkill.join(
                                PMove.dealDamage(amount),
                                PMove.applyToSingle(extra, PCLPowerHelper.Vulnerable)
                        );
                    case DEFEND_BUFF:
                        return PMultiSkill.join(
                                PMove.dealDamage(amount),
                                PMove.gain(extra, PCLPowerHelper.PlatedArmor)
                        );
                    case ESCAPE:
                        return PMultiSkill.join(
                                PMove.dealDamage(3 * amount)
                        );
                    case SLEEP:
                    case STUN:
                        return PMove.gainPlayer(extra, PCLPowerHelper.Energized);
                    case UNKNOWN:
                        return PMove.gainTempHP(extra);
                    case MAGIC:
                        return PMove.gain(extra, PCLPowerHelper.Sorcery);
                    default:
                        return PMove.gain(extra, PCLPowerHelper.Malleable);
                }
            }
            return null;
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

        @Override
        public String getText(PCLCardTarget perpsective, boolean addPeriod)
        {
            if (actualMove != null)
            {
                return actualMove.getText(perpsective, addPeriod);
            }
            return super.getText(perpsective, addPeriod);
        }


        @Override
        public void onDrag(AbstractMonster m)
        {
            super.onDrag(m);
            tryChangeEffect(m);

            if (actualMove != null)
            {
                actualMove.onDrag(m);
            }
        }

        @Override
        public void refresh(PCLUseInfo info, boolean conditionMet)
        {
            super.refresh(info, conditionMet);
            tryChangeEffect(info.target);
        }
    }
}

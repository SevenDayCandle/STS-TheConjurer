package pinacolada.cards.conjurer.series.honkai;


import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.CombatManager;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.interfaces.subscribers.OnAllyDeathSubscriber;
import pinacolada.interfaces.subscribers.OnAllySummonSubscriber;
import pinacolada.interfaces.subscribers.OnAllyWithdrawSubscriber;
import pinacolada.monsters.PCLCardAlly;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.powers.conjurer.CooledPower;
import pinacolada.powers.conjurer.FlowPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.skills.skills.PCustomCond;
import pinacolada.skills.skills.PMultiSkill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@VisibleCard
public class KianaKaslana extends PCLCard {
    public static final PCLCardData DATA = register(KianaKaslana.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Ranged)
            .setDamage(2, 2)
            .setHp(5, 1)
            .setAffinities(PCLAffinity.Star)
            .setLoadout(ConjurerPlayerData.honkai);

    public KianaKaslana() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.ELECTRIC);
        addUseMove(new KianaKaslanaCond(DATA, 1, 1));
    }

    protected static class KianaKaslanaCond extends PCustomCond implements OnAllyDeathSubscriber, OnAllySummonSubscriber, OnAllyWithdrawSubscriber {
        protected PMultiSkill current;

        public KianaKaslanaCond(PCustomCond other) {
            super(other);
        }

        public KianaKaslanaCond(PCLCardData data, int amount, int extra) {
            super(data, 0, amount, extra);
        }

        protected PMultiSkill getActualMove() {
            ArrayList<PSkill<?>> effects = new ArrayList<>();

            HashMap<PCLAffinity, Integer> counts = new HashMap<>();
            for (PCLCardAlly summon : CombatManager.summons.summons) {
                if (summon.card != null && summon.card != sourceCard) {
                    for (PCLCardAffinity affinity : summon.card.affinities.sorted) {
                        counts.merge(affinity.type, amount, Integer::sum);
                    }
                }
            }

            for (Map.Entry<PCLAffinity, Integer> entry : counts.entrySet()) {
                switch (entry.getKey().ID) {
                    case PCLAffinity.ID_RED:
                        effects.add(PMove.applyToSingle(entry.getValue() + extra, BlastedPower.DATA));
                        break;
                    case PCLAffinity.ID_BLUE:
                        effects.add(PMove.applyToSingle(entry.getValue() + extra, CooledPower.DATA));
                        break;
                    case PCLAffinity.ID_GREEN:
                        effects.add(PMove.gainPlayer(entry.getValue() + extra, FlowPower.DATA));
                        break;
                    case PCLAffinity.ID_ORANGE:
                        effects.add(PMove.gainBlockPlayer(entry.getValue() + extra));
                        break;
                    default:
                        effects.add(CMove.gainMatter((entry.getValue() + extra) * 2));
                }
            }

            return new PMultiSkill(effects);
        }

        @Override
        public String getText(PCLCardTarget perpsective, boolean addPeriod) {
            if (current != null && current.getSubEffects().size() > 0) {
                return current.getText(perpsective, addPeriod);
            }
            return super.getText(perpsective, addPeriod);
        }

        @Override
        public void onAllyDeath(PCLCard pclCard, PCLCardAlly pclCardAlly) {
            current = null;
        }

        @Override
        public void onAllySummon(PCLCardAlly pclCardAlly, PCLCard pclCard, PCLCard pclCard1) {
            current = null;
        }

        @Override
        public void onAllyWithdraw(PCLCard pclCard, PCLCardAlly pclCardAlly) {
            current = null;
        }

        @Override
        public void onDrag(AbstractMonster m) {
            super.onDrag(m);
            tryChangeEffect();
            if (current != null) {
                current.onDrag(m);
            }
        }

        @Override
        public void refresh(PCLUseInfo info, boolean conditionMet, boolean isUsing) {
            super.refresh(info, conditionMet, isUsing);
            tryChangeEffect();
        }

        protected void tryChangeEffect() {
            if (current == null) {
                current = getActualMove();
                sourceCard.initializeDescription();
            }
        }

        @Override
        protected void useImpl(PCLUseInfo info, PCLActions order) {
            current = null;
            tryChangeEffect();
            if (current != null) {
                current.use(info, order);
            }
        }
    }
}

package pinacolada.cards.conjurer.series.genshinimpact;


import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.interfaces.subscribers.OnBlockGainedSubscriber;
import pinacolada.interfaces.subscribers.OnCreatureHealSubscriber;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PSkill;

@VisibleCard
public class KokomiSangonomiya extends PCLCard {
    public static final PCLCardData DATA = register(KokomiSangonomiya.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Immaterial)
            .setDamage(1, 2)
            .setHp(3, 1)
            .setAffinities(2, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.genshinImpact, true);

    public KokomiSangonomiya() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.WATER).setBonus(PMod.perCreatureHP(PCLCardTarget.Self, 2), 1);
        addSpecialPower(0, (s, i) -> new KokomiSangonomiyaPower(i.source, i.source, s), 3, 1);
    }

    public static class KokomiSangonomiyaPower extends PSpecialCardPower implements OnBlockGainedSubscriber, OnCreatureHealSubscriber {
        public static final PCLPowerData PDATA = createFromCard(KokomiSangonomiyaPower.class, DATA);

        public KokomiSangonomiyaPower(AbstractCreature owner, AbstractCreature source, PSkill<?> move) {
            super(PDATA, owner, source, move);
        }

        @Override
        public int onBlockGained(AbstractCreature c, int block) {
            if (block > 0 && c != AbstractDungeon.player) {
                PCLActions.bottom.gainTemporaryHP(owner, owner, block);
            }
            return 0;
        }

        @Override
        public int onHeal(AbstractCreature c, int heal) {
            if (heal > 0 && c != AbstractDungeon.player) {
                PCLActions.bottom.gainTemporaryHP(owner, owner, heal);
            }
            return 0;
        }
    }
}

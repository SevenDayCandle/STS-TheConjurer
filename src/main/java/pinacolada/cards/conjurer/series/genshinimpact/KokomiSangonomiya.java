package pinacolada.cards.conjurer.series.genshinimpact;


import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.interfaces.subscribers.OnBlockGainedSubscriber;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PSkill;

@VisibleCard
public class KokomiSangonomiya extends PCLCard {
    public static final PCLCardData DATA = register(KokomiSangonomiya.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Immaterial)
            .setDamage(1, 0)
            .setHp(2, 1)
            .setAffinities(2, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.genshinImpact, true);

    public KokomiSangonomiya() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.WATER).setBonus(PMod.perCreatureHP(PCLCardTarget.Self, 4).setUpgrade(-1), 1);
        addSpecialPower(0, (s, i) -> new KokomiSangonomiyaPower(i.source, s), 3, 1);
    }

    public static class KokomiSangonomiyaPower extends PSpecialCardPower implements OnBlockGainedSubscriber {
        public KokomiSangonomiyaPower(AbstractCreature owner, PSkill<?> move) {
            super(DATA, owner, move);
        }

        @Override
        public int onBlockGained(AbstractCreature abstractCreature, int block) {
            if (block > 0) {
                PCLActions.bottom.gainTemporaryHP(owner, owner, block);
            }
            return 0;
        }
    }
}

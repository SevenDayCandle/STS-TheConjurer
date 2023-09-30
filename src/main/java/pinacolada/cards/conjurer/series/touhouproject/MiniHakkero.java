package pinacolada.cards.conjurer.series.touhouproject;


import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.interfaces.subscribers.OnModifyDamageGiveLastSubscriber;
import pinacolada.monsters.PCLCardAlly;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;

@VisibleCard
public class MiniHakkero extends PCLCard {
    public static final PCLCardData DATA = register(MiniHakkero.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE, PCLCardTarget.Team)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public MiniHakkero() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.gain(1, PCLPowerData.Critical));
        addSpecialPower(0, (s, i) -> new MiniHakkeroPower(i.source, i.source, s), 2, 35).setUpgrade(1);
    }

    public static class MiniHakkeroPower extends PSpecialCardPower implements OnModifyDamageGiveLastSubscriber {
        public static final PCLPowerData PDATA = createFromCard(MiniHakkeroPower.class, DATA)
                .setEndTurnBehavior(PCLPowerData.Behavior.TurnBased);

        public MiniHakkeroPower(AbstractCreature owner, AbstractCreature source, PSkill<?> move) {
            super(PDATA, owner, source, move);
        }

        @Override
        public float onModifyDamageGiveLast(float amount, DamageInfo.DamageType type, AbstractCreature source, AbstractCreature target, AbstractCard card) {
            return source instanceof PCLCardAlly ? amount * (100 + move.extra) / 100f : amount;
        }
    }
}

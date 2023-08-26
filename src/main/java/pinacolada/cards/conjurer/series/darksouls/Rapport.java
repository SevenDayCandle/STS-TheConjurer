package pinacolada.cards.conjurer.series.darksouls;


import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.interfaces.subscribers.OnModifyDamageGiveLastSubscriber;
import pinacolada.monsters.PCLCardAlly;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;

@VisibleCard
public class Rapport extends PCLCard {
    public static final PCLCardData DATA = register(Rapport.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Team)
            .setAffinities(PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public Rapport() {
        super(DATA);
    }

    public void setup(Object input) {
        addSpecialPower(0, (s, i) -> new RapportPower(i.source, s), 2, 35);
        addUseMove(PMove.triggerAlly(PCLCardTarget.RandomAlly, 1).setUpgrade(2));
    }

    public static class RapportPower extends PSpecialCardPower implements OnModifyDamageGiveLastSubscriber {
        public RapportPower(AbstractCreature owner, PSkill<?> move) {
            super(Rapport.DATA, owner, move);
            initialize(move.amount, PowerType.BUFF, true);
        }

        @Override
        public float onModifyDamageGiveLast(float amount, DamageInfo.DamageType type, AbstractCreature source, AbstractCreature target, AbstractCard card) {
            return source instanceof PCLCardAlly ? amount * (100 + move.extra) / 100f : amount;
        }
    }
}

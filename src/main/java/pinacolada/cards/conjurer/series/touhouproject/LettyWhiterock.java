package pinacolada.cards.conjurer.series.touhouproject;


import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.interfaces.subscribers.OnModifyDamageGiveFirstSubscriber;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class LettyWhiterock extends PCLCard {
    public static final PCLCardData DATA = register(LettyWhiterock.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Immaterial)
            .setDamage(2, 1)
            .setHp(5, 2)
            .setRTags(PCLCardTag.Ethereal)
            .setAffinities(2, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public LettyWhiterock() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.ICE);
        addSpecialPower(0, (s, i) -> new LettyWhiterockPower(i.source, s), 35);
    }

    public static class LettyWhiterockPower extends PSpecialCardPower implements OnModifyDamageGiveFirstSubscriber {
        public LettyWhiterockPower(AbstractCreature owner, PSkill<?> move) {
            super(LettyWhiterock.DATA, owner, move);
        }

        @Override
        public float onModifyDamageGiveFirst(float amount, DamageInfo.DamageType type, AbstractCreature source, AbstractCreature target, AbstractCard card) {
            if (card != null) {
                if (GameUtilities.hasAffinity(card, PCLAffinity.Blue)) {
                    amount = amount * (100 + move.amount) / 100f;
                }
                if (GameUtilities.hasAffinity(card, PCLAffinity.Red)) {
                    amount = amount * (100 - move.amount) / 100f;
                }
            }
            return amount;
        }
    }
}

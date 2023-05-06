package pinacolada.cards.conjurer.series.genshinimpact;


import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.monsters.PCLCardAlly;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;

@VisibleCard
public class Wanderer extends PCLCard {
    public static final PCLCardData DATA = register(Wanderer.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Normal, PCLCardTarget.RandomEnemy)
            .setDamage(1, 0, 2)
            .setRTags(PCLCardTag.Ethereal)
            .setHp(5, 1)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.genshinImpact, true);

    public Wanderer() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.DAGGER);
        addSpecialPower(0, (s, i) -> new WandererPower(i.source, s), 3, 1);
    }

    public static class WandererPower extends PSpecialCardPower {
        public WandererPower(AbstractCreature owner, PSkill<?> move) {
            super(DATA, owner, move);
        }

        @Override
        public void onUseCard(AbstractCard card, UseCardAction action) {
            if ((card.type == CardType.SKILL || card.type == CardType.CURSE) && owner instanceof PCLCardAlly) {
                ((PCLCardAlly) owner).takeTurn();
                this.flash();
            }
        }
    }
}

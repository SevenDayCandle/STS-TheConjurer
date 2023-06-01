package pinacolada.cards.conjurer.colorless;

import com.megacrit.cardcrawl.cards.AbstractCard;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLMultiCard;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerResources;

@VisibleCard
public class Polymerization extends PCLMultiCard {
    public static final PCLCardData DATA = register(Polymerization.class, ConjurerResources.conjurer)
            .setSkill(-2, CardRarity.RARE, PCLCardTarget.None)
            .setAffinities(PCLAffinity.Star.make(2))
            .setColorless();

    public Polymerization() {
        super(DATA);
    }

    @Override
    protected PCLMultiCardMove createMulticardMove() {
        return new PCLMultiCardMove(DATA, this, 2);
    }

    @Override
    public boolean acceptCard(AbstractCard card) {
        return card.type == CardType.ATTACK || card.type == CardType.SKILL;
    }
}
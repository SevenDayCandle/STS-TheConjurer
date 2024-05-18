package pinacolada.cards.conjurer.colorless;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLMultiCard;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerResources;

@VisibleCard
public class Polymerization extends PCLMultiCard {
    public static final PCLCardData DATA = register(Polymerization.class, ConjurerResources.conjurer)
            .setSkill(-2, CardRarity.SPECIAL, PCLCardTarget.Single)
            .setAffinities(PCLAffinity.Star.make(1))
            .setColorless()
            .setObtainableInCombat(false);

    public Polymerization() {
        super(DATA);
    }

    @Override
    protected PCLMultiCardMove createMulticardMove() {
        return new PCLMultiCardMove(DATA, this, 2)
                .edit(f -> f.setType(CardType.ATTACK, CardType.SKILL));
    }
}
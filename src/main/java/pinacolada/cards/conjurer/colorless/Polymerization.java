package pinacolada.cards.conjurer.colorless;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLMultiCard;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.fields.PField_CardCategory;

@VisibleCard
public class Polymerization extends PCLMultiCard {
    public static final PCLCardData DATA = register(Polymerization.class, ConjurerResources.conjurer)
            .setSkill(-2, CardRarity.RARE, PCLCardTarget.Single)
            .setAffinities(PCLAffinity.Star.make(1))
            .setColorless()
            .setObtainableInCombat(false);

    public Polymerization() {
        super(DATA);
    }

    @Override
    public PField_CardCategory createFilterFields() {
        return new PField_CardCategory().setType(CardType.ATTACK, CardType.SKILL);
    }

    @Override
    protected PCLMultiCardMove createMulticardMove() {
        return new PCLMultiCardMove(DATA, this, 2);
    }
}
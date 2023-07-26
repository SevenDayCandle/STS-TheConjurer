package pinacolada.cards.conjurer.colorless;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLMultiCard;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.fields.PField_CardCategory;

@VisibleCard
public class Kirby extends PCLMultiCard {
    public static final PCLCardData DATA = register(Kirby.class, ConjurerResources.conjurer)
            .setSummon(-2, CardRarity.RARE)
            .setHp(1, 0)
            .setAffinities(PCLAffinity.Star.make(1))
            .setColorless()
            .setObtainableInCombat(false);

    public Kirby() {
        super(DATA);
    }

    @Override
    protected PCLMultiCardMove createMulticardMove() {
        return new PCLMultiCardMove(DATA, this, 2);
    }

    @Override
    public PField_CardCategory createFilterFields() {
        return new PField_CardCategory().setType(PCLEnum.CardType.SUMMON).setRarity(CardRarity.COMMON);
    }
}
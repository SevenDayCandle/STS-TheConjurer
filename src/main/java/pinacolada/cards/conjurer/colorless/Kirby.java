package pinacolada.cards.conjurer.colorless;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLMultiCard;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;

@VisibleCard
public class Kirby extends PCLMultiCard {
    public static final PCLCardData DATA = register(Kirby.class, ConjurerResources.conjurer)
            .setSummon(-2, CardRarity.RARE)
            .setHp(1, 0)
            .setAffinities(PCLAffinity.Star.make(1))
            .setLoadout(ConjurerPlayerData.kirby, true)
            .setObtainableInCombat(false);

    public Kirby() {
        super(DATA);
    }

    @Override
    protected PCLMultiCardMove createMulticardMove() {
        return new PCLMultiCardMove(DATA, this, 2)
                .edit(f -> f.setType(PCLEnum.CardType.SUMMON).setRarity(CardRarity.COMMON));
    }
}
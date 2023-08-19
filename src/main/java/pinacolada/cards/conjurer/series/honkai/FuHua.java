package pinacolada.cards.conjurer.series.honkai;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class FuHua extends PCLCard {
    public static final PCLCardData DATA = register(FuHua.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON)
            .setDamage(4, 1)
            .setHp(5, 1)
            .setAffinities(PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.honkai);

    public FuHua() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SLASH_HORIZONTAL);
        addUseMove(CCond.link(PCLAffinity.Blue), PCond.cycleRandom(1), PMove.gainBlockPlayer(3));
    }
}

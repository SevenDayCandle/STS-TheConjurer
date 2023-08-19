package pinacolada.cards.conjurer.series.honkai;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;

@VisibleCard
public class HimekoMurata extends PCLCard {
    public static final PCLCardData DATA = register(HimekoMurata.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.UNCOMMON)
            .setDamage(6, 1)
            .setHp(7, 1)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.honkai);

    public HimekoMurata() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.FIRE);
        addUseMove(CCond.linkBack(PCLAffinity.Blue, PCLAffinity.Orange), PMove.applyToSingle(1, PCLElementHelper.Ignis));
    }
}

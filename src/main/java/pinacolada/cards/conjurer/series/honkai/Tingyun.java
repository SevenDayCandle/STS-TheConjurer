package pinacolada.cards.conjurer.series.honkai;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.CMove;

@VisibleCard
public class Tingyun extends PCLCard {
    public static final PCLCardData DATA = register(Tingyun.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.COMMON)
            .setDamage(4, 1)
            .setHp(3, 1)
            .setAffinities(PCLAffinity.Orange, PCLAffinity.Yellow, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.honkai);

    public Tingyun() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.BLUNT_LIGHT);
        addUseMove(CCond.link(), CMove.gainMatter(4).setUpgrade(1));
    }
}

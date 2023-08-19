package pinacolada.cards.conjurer.series.honkai;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class FuXuan extends PCLCard {
    public static final PCLCardData DATA = register(FuXuan.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Immaterial)
            .setDamage(2, 0)
            .setHp(6, 1)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.honkai);

    public FuXuan() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.PSYCHOKINESIS);
        addUseMove(CCond.linkBack(PCLAffinity.Blue), PMod.scryPer(1).setUpgrade(1), PMove.gainPlayer(1, PCLPowerHelper.NextTurnDraw));
    }
}

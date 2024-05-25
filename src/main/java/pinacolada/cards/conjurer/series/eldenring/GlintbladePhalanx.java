package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.common.WardingPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.skills.base.moves.PMove_StabilizePower;

@VisibleCard
public class GlintbladePhalanx extends PCLCard {
    public static final PCLCardData DATA = register(GlintbladePhalanx.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON)
            .setDamage(4, 1, 3)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public GlintbladePhalanx() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove();
        addUseMove(new PMove_StabilizePower(PCLCardTarget.SelfSingle, WardingPower.DATA));
    }
}

package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.CooledPower;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class GiantsflameTakeThee extends PCLCard {
    public static final PCLCardData DATA = register(GiantsflameTakeThee.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON)
            .setDamage(14, 3)
            .setAffinities(2, PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public GiantsflameTakeThee() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SMALL_EXPLOSION).setBonus(PMod.perPowerSingle(CooledPower.DATA), 2,1);
        addUseMove(PMove.applyToSingle(2, IgnisPower.DATA));
    }
}

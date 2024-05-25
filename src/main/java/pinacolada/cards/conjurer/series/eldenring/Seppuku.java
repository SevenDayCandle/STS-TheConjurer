package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrait;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Seppuku extends PCLCard {
    public static final PCLCardData DATA = register(Seppuku.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE, PCLCardTarget.Self)
            .setCostUpgrades(-1)
            .setAffinities(1, PCLAffinity.Red, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public Seppuku() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.gain(2, PCLPowerData.Bruised));
        addGainPower(1, PTrigger.passive(PMod.perDistinctDebuff(PCLCardTarget.Self, 1), PTrait.damageMultiplier(50)));
    }
}

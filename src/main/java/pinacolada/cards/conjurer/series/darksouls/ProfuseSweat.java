package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.powers.conjurer.ElementPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class ProfuseSweat extends PCLCard {
    public static final PCLCardData DATA = register(ProfuseSweat.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.UNCOMMON, PCLCardTarget.Self)
            .setBlock(1, 1, 3, 0)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public ProfuseSweat() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
        addUseMove(PCond.checkPowerSelf(1, AquaPower.DATA, ElementPowerData.Weak).edit(f -> f.setRandom(true)), PMove.applyToEnemies(2, PCLPowerData.Weak));
    }
}

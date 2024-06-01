package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.powers.conjurer.CooledPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.conjurer.modifiers.PMod_PerLevel;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class FloatingChaos extends PCLCard {
    public static final PCLCardData DATA = register(FloatingChaos.class, ConjurerResources.conjurer)
            .setPower(2, CardRarity.UNCOMMON)
            .setCostUpgrades(-1)
            .setAffinities(1, PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public FloatingChaos() {
        super(DATA);
    }

    public void setup(Object input) {
        addGainPower(PTrigger.when(PCond.onTurnEnd(), new PMod_PerLevel(2, PCLAffinity.Red), PMove.applyToEnemies(1, BlastedPower.DATA)));
    }
}

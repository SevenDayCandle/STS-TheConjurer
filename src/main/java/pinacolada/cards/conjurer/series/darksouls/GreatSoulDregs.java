package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class GreatSoulDregs extends PCLCard {
    public static final PCLCardData DATA = register(GreatSoulDregs.class, ConjurerResources.conjurer)
            .setPower(2, CardRarity.RARE)
            .setAffinities(2, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public GreatSoulDregs() {
        super(DATA);
    }

    public void setup(Object input) {
        addGainPower(PTrigger.when(PCond.onExhaust(), PMove.applyToEnemies(2, PCLPowerData.Weak, PCLPowerData.Vulnerable, PCLPowerData.Blinded).setUpgrade(1).edit(f -> f.setRandom(true)))).setVFX(ConjurerEFK.DARK03);
    }
}

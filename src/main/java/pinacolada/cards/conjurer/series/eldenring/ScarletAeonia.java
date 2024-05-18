package pinacolada.cards.conjurer.series.eldenring;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class ScarletAeonia extends PCLCard {
    public static final PCLCardData DATA = register(ScarletAeonia.class, ConjurerResources.conjurer)
            .setPower(2, CardRarity.RARE)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setCostUpgrades(-1)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public ScarletAeonia() {
        super(DATA);
    }

    public void setup(Object input) {
        addGainPower(PTrigger.when(PCond.onTurnEnd(), PMod.perPowerAny(PCLPowerData.Vulnerable), PMove.loseHp(PCLCardTarget.AllEnemy, 6).setUpgrade(2))).setVFX(ConjurerEFK.EVFXForge02_08_BloomforgeWard);
    }
}

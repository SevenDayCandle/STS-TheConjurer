package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.powers.conjurer.CooledPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class ZamorIceStorm extends PCLCard {
    public static final PCLCardData DATA = register(ZamorIceStorm.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.UNCOMMON, PCLCardTarget.AllEnemy)
            .setAffinities(2, PCLAffinity.Blue)
            .setTags(PCLCardTag.Exhaust)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public ZamorIceStorm() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.applyToEnemies(12, CooledPower.DATA).setUpgrade(4));
        addGainPower(3, PTrigger.when(PCond.onTurnStart(), PMove.applyToEnemies(2, AquaPower.DATA, CooledPower.DATA).setUpgrade(1)));
    }
}

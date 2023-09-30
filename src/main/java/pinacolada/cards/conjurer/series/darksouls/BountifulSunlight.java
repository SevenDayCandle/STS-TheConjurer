package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class BountifulSunlight extends PCLCard {
    public static final PCLCardData DATA = register(BountifulSunlight.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.RARE, PCLCardTarget.Self)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public BountifulSunlight() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.gainTempHP(8).setUpgrade(3));
        addGainPower(2, PTrigger.when(PCond.onTurnStart(), PMove.gainTempHP(PCLCardTarget.Team, 4)));
    }
}

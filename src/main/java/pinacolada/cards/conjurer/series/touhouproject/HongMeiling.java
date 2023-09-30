package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class HongMeiling extends PCLCard {
    public static final PCLCardData DATA = register(HongMeiling.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON)
            .setDamage(2, 0)
            .setHp(8, 3)
            .setAffinities(1, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.touhouProject, true);

    public HongMeiling() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.BLUNT_LIGHT);
        addGainPower(PTrigger.when(PCond.haveTakenDamage(), PMove.gainPlayer(4, PCLPowerData.NextTurnBlock).setUpgrade(1)));
    }
}

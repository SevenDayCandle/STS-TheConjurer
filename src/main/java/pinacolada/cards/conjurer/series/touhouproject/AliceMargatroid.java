package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PCond;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class AliceMargatroid extends PCLCard {
    public static final PCLCardData DATA = register(AliceMargatroid.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.UNCOMMON, PCLAttackType.Immaterial)
            .setDamage(3, 1)
            .setHp(8, 0)
            .setAffinities(PCLAffinity.Blue.make(2), PCLAffinity.Yellow.make())
            .setLoadout(ConjurerPlayerData.touhouProject);

    public AliceMargatroid() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.WIND);
        addGainPower(PTrigger.when(PCond.onCreate(), CMove.addLevel(2, PCLAffinity.Blue).setUpgrade(1)));
    }
}

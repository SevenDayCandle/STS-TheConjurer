package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.monsters.PCLIntentType;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;

@VisibleCard
public class Parry extends PCLCard {
    public static final PCLCardData DATA = register(Parry.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON, PCLCardTarget.Single)
            .setBlock(4, 2)
            .setAffinities(PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public Parry() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove().setBonus(PMod.perCreatureIntent(PCLCardTarget.AllEnemy, PCLIntentType.Attack), 3, 1);
    }
}

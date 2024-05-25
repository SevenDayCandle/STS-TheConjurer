package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.monsters.PCLIntentType;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class TwistedWallOfLight extends PCLCard {
    public static final PCLCardData DATA = register(TwistedWallOfLight.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.AllEnemy)
            .setBlock(7, 3)
            .setAffinities(PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public TwistedWallOfLight() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
        addUseMove(PMod.perCreatureIntent(PCLCardTarget.AllEnemy, PCLIntentType.Debuff), PMove.modifyBlock(3).setUpgrade(1));
    }
}

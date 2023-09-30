package pinacolada.cards.conjurer.series.monsterhunter;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class Lifepowder extends PCLCard {
    public static final PCLCardData DATA = register(Lifepowder.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON, PCLCardTarget.Team)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public Lifepowder() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.gainTempHP(4).setUpgrade(2));
        addUseMove(PMove.heal(PCLCardTarget.AllAlly, 3).setUpgrade(1));
    }
}

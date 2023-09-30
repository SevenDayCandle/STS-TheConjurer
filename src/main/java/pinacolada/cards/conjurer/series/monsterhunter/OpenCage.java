package pinacolada.cards.conjurer.series.monsterhunter;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class OpenCage extends PCLCard {
    public static final PCLCardData DATA = register(OpenCage.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.UNCOMMON)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Silver)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public OpenCage() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.triggerAlly(PCLCardTarget.AllAlly, 2).setUpgrade(1));
        addUseMove(PMove.withdrawAlly(PCLCardTarget.AllAlly, 2));
    }
}

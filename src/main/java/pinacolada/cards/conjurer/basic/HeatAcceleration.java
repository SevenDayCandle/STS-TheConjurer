package pinacolada.cards.conjurer.basic;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class HeatAcceleration extends PCLCard {
    public static final PCLCardData DATA = register(HeatAcceleration.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.RARE, PCLCardTarget.Team)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green)
            .setCore();

    public HeatAcceleration() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMod.bonusPerCreature(PCLCardTarget.AllAlly, -1), PMove.draw(3).setUpgrade(1));
        addUseMove(PMove.triggerAlly(PCLCardTarget.AllAlly, 1));
    }
}

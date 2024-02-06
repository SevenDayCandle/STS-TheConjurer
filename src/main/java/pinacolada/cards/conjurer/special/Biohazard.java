package pinacolada.cards.conjurer.special;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Biohazard extends PCLCard {
    public static final PCLCardData DATA = register(Biohazard.class, ConjurerResources.conjurer)
            .setStatus(0, CardRarity.SPECIAL, PCLCardTarget.SelfAllEnemy)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange);

    public Biohazard() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PCond.onExhaust(), PMove.applyToEveryone(2, PCLPowerData.Vulnerable).setUpgrade(2));
    }
}
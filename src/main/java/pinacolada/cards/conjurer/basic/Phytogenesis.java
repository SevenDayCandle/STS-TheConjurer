package pinacolada.cards.conjurer.basic;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Phytogenesis extends PCLCard {
    public static final PCLCardData DATA = register(Phytogenesis.class, ConjurerResources.conjurer)
            .setPower(1, CardRarity.UNCOMMON)
            .setAffinities(1, PCLAffinity.Green, PCLAffinity.Orange)
            .setCore();

    public Phytogenesis() {
        super(DATA);
    }

    public void setup(Object input) {
        addGainPower(PTrigger.when(PCond.haveLostHP(), PMultiSkill.join(PMove.gainBlock(2).setUpgrade(1), CMove.gainMatter(5))));
    }
}

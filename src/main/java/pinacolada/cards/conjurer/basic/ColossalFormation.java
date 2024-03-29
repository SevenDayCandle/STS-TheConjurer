package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PTrait;

@VisibleCard
public class ColossalFormation extends PCLCard {
    public static final PCLCardData DATA = register(ColossalFormation.class, ConjurerResources.conjurer)
            .setSkill(3, CardRarity.COMMON, PCLCardTarget.Self)
            .setBlock(17, 3)
            .setAffinities(2, PCLAffinity.Orange)
            .setCore();

    public ColossalFormation() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove().setChain(PCond.pileHas(6, PCLCardGroupHelper.Hand), PTrait.block(13).setUpgrade(3));
    }
}

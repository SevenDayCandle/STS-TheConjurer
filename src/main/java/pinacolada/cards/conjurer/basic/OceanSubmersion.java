package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMod;
import pinacolada.skills.PMove;

@VisibleCard
public class OceanSubmersion extends PCLCard {
    public static final PCLCardData DATA = register(OceanSubmersion.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Team)
            .setBlock(7, 2)
            .setAffinities(2, PCLAffinity.Blue)
            .setCore();

    public OceanSubmersion() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove(PCLCardTarget.Team);
        addUseMove(PMove.applyToEveryone(1, AquaPower.DATA));
    }
}

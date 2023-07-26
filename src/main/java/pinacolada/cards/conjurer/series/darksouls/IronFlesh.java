package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PTrait;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class IronFlesh extends PCLCard {
    public static final PCLCardData DATA = register(IronFlesh.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE, PCLCardTarget.Self)
            .setBlock(4, 3)
            .setAffinities(PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public IronFlesh() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
        addGainPower(2, PTrigger.passive(PTrait.takeDamageMultiplier(-20)));
    }
}

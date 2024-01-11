package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.conjurer.special.Overheat;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class Incineration extends PCLCard {
    public static final PCLCardData DATA = register(Incineration.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Team)
            .setAffinities(2, PCLAffinity.Red)
            .setCore();

    public Incineration() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.apply(PCLCardTarget.Team, 5, PCLPowerData.Vigor).setUpgrade(2));
        addUseMove(PMod.perCreature(PCLCardTarget.AllAlly, 1), PMove.createDrawPile(1, Overheat.DATA.ID));
    }
}

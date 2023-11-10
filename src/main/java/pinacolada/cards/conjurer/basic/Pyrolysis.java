package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardSelection;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.conjurer.special.Overheat;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Pyrolysis extends PCLCard {
    public static final PCLCardData DATA = register(Pyrolysis.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.RARE, PCLCardTarget.Self)
            .setAffinities(PCLAffinity.Red.make(1, 1))
            .setCore();

    public Pyrolysis() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.createDrawPile(2, Overheat.DATA.ID).edit(f -> f.setDestination(PCLCardSelection.Top)));
        addGainPower(2, PTrigger.when(PCond.onDraw().edit(f -> f.setType(CardType.STATUS)), PMove.applyToEnemies(5, BlastedPower.DATA).setUpgrade(1)));
    }
}

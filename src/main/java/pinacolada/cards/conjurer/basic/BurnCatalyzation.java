package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardSelection;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.pcl.status.Status_Burn;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class BurnCatalyzation extends PCLCard {
    public static final PCLCardData DATA = register(BurnCatalyzation.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.RARE, PCLCardTarget.Self)
            .setAffinities(1, PCLAffinity.Red)
            .setCore();

    public BurnCatalyzation() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.createDrawPile(2, Status_Burn.DATA.ID).edit(f -> f.setDestination(PCLCardSelection.Top)));
        addGainPower(2, PTrigger.when(PCond.onDraw().edit(f -> f.setType(CardType.STATUS)),
                PMultiSkill.join(PMove.draw(1), PMove.applyToEnemies(3, IgnisPower.DATA, BlastedPower.DATA).setUpgrade(2))));
    }
}

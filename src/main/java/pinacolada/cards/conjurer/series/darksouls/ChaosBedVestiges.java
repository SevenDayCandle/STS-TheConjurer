package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardSelection;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.pcl.status.Status_Burn;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class ChaosBedVestiges extends PCLCard {
    public static final PCLCardData DATA = register(ChaosBedVestiges.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.RARE, PCLCardTarget.Self)
            .setAffinities(PCLAffinity.Red.make(1, 1))
            .setLoadout(ConjurerPlayerData.darkSouls);

    public ChaosBedVestiges() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.createDrawPile(2, Status_Burn.DATA.ID).edit(f -> f.setDestination(PCLCardSelection.Top)));
        addGainPower(2, PTrigger.when(PCond.onDraw().edit(f -> f.setType(CardType.STATUS)),
                PMultiSkill.join(PMove.draw(1), PMove.applyToEnemies(3, BlastedPower.DATA).setUpgrade(1))));
    }
}

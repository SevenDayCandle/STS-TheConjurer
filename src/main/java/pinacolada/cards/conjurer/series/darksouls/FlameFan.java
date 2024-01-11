package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.powers.conjurer.VentusPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class FlameFan extends PCLCard {
    public static final PCLCardData DATA = register(FlameFan.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Single)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public FlameFan() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.applyToSingle(2, IgnisPower.DATA, BlastedPower.DATA).setUpgrade(1));
        addUseMove(PMove.spreadPower(PCLCardTarget.Single, IgnisPower.DATA, BlastedPower.DATA));
    }
}

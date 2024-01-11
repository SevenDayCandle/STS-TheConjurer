package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class GreatCombustion extends PCLCard {
    public static final PCLCardData DATA = register(GreatCombustion.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public GreatCombustion() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.applyToSingle(3, BlastedPower.DATA).setUpgrade(1));
        addUseMove(PMod.perPowerSingle(BlastedPower.DATA), PMove.dealDamageToAll(1));
    }
}

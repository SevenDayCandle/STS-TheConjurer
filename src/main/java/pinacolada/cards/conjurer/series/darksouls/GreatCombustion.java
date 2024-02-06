package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class GreatCombustion extends PCLCard {
    public static final PCLCardData DATA = register(GreatCombustion.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.UNCOMMON)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public GreatCombustion() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMod.perPowerSingle(BlastedPower.DATA), PMove.dealDamageToAll(1));
        addUseMove(PMove.applyToSingle(5, BlastedPower.DATA).setUpgrade(2));
    }
}

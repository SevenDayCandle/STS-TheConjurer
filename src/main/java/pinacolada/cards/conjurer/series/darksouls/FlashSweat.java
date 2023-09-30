package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.powers.conjurer.ElementPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;

@VisibleCard
public class FlashSweat extends PCLCard {
    public static final PCLCardData DATA = register(FlashSweat.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.COMMON, PCLCardTarget.Self)
            .setBlock(4, 2)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public FlashSweat() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove().setBonus(PCond.checkPowerSelf(1, BlastedPower.DATA, ElementPowerData.Weak).edit(f -> f.setRandom(true)), 3, 2);
    }
}

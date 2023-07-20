package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class SnapFreeze extends PCLCard {
    public static final PCLCardData DATA = register(SnapFreeze.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public SnapFreeze() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.applyToSingle(5, PCLElementHelper.Frostbite).setUpgrade(2));
        addUseMove(PMod.perPowerSingle(PCLElementHelper.Frostbite), PMove.dealDamage(1));
    }
}

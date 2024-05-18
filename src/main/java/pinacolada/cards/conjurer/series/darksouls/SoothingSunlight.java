package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.powers.conjurer.VentusPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.moves.PMove_StabilizePower;

@VisibleCard
public class SoothingSunlight extends PCLCard {
    public static final PCLCardData DATA = register(SoothingSunlight.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.UNCOMMON, PCLCardTarget.Self)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public SoothingSunlight() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMod.bonusPerPowerSelf(1).edit(f -> f.setDebuff(true)), PMove.gainTempHP(4).setUpgrade(1));
    }
}

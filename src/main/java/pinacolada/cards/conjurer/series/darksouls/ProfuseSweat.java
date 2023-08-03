package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.CMove;
import pinacolada.skills.PMod;

@VisibleCard
public class ProfuseSweat extends PCLCard {
    public static final PCLCardData DATA = register(ProfuseSweat.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Self)
            .setBlock(2, 0, 3, 1)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public ProfuseSweat() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
        addUseMove(CCond.react(), PMod.perLevel(1, PCLAffinity.Red, PCLAffinity.Blue), CMove.gainMatter(5).setUpgrade(2));
    }
}

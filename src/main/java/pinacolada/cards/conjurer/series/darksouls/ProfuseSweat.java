package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrait;

@VisibleCard
public class ProfuseSweat extends PCLCard {
    public static final PCLCardData DATA = register(ProfuseSweat.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.UNCOMMON, PCLCardTarget.Self)
            .setBlock(1, 1, 3, 0)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public ProfuseSweat() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove().setChain(PCond.checkPowerSelf(1, PCLElementHelper.Ignis, PCLElementHelper.Aqua).edit(f -> f.setDebuff(true)), PTrait.blockCount(2));
        addUseMove(PMove.remove(PCLCardTarget.Self, PCLElementHelper.Ignis, PCLElementHelper.Aqua));
    }
}

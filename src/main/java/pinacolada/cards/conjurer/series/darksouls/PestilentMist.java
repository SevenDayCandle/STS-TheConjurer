package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;

@VisibleCard
public class PestilentMist extends PCLCard {
    public static final PCLCardData DATA = register(PestilentMist.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.AllEnemy)
            .setRTags(PCLCardTag.Ethereal)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public PestilentMist() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.applyToEnemies(3, PCLPowerHelper.Weak));
        addUseMove(CCond.react(), PMove.gain(1, PCLPowerHelper.Blur));
    }
}

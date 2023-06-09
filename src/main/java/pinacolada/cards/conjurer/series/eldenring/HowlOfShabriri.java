package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

@VisibleCard
public class HowlOfShabriri extends PCLCard {
    public static final PCLCardData DATA = register(HowlOfShabriri.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.UNCOMMON, PCLCardTarget.Self)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green)
            .setUTags(PCLCardTag.Haste, PCLCardTag.Bounce)
            .setLoadout(ConjurerPlayerData.eldenRing, true);

    public HowlOfShabriri() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMultiSkill.join(PMove.gain(1, PCLPowerHelper.Bruised), PMove.apply(PCLCardTarget.AllEnemy, 2, PCLPowerHelper.Bruised, PCLPowerHelper.Vulnerable).setUpgrade(1)));
    }
}

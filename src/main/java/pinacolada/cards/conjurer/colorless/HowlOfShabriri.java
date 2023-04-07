package pinacolada.cards.conjurer.colorless;


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
public class HowlOfShabriri extends PCLCard
{
    public static final PCLCardData DATA = register(HowlOfShabriri.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.UNCOMMON, PCLCardTarget.None)
            .setAffinities(PCLAffinity.Star)
            .setUTags(PCLCardTag.Haste, PCLCardTag.Bounce)
            .setLoadout(ConjurerPlayerData.eldenRing, true);

    public HowlOfShabriri()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMultiSkill.join(PMove.apply(PCLCardTarget.Any, 3, PCLPowerHelper.Bruised), PMove.apply(PCLCardTarget.Any, 3, PCLPowerHelper.Vulnerable), PMove.apply(PCLCardTarget.AllEnemy, 1, PCLPowerHelper.Vulnerable)));
    }
}
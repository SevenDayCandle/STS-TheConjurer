package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

@VisibleCard
public class GreatbladePhalanx extends PCLCard
{
    public static final PCLCardData DATA = register(GreatbladePhalanx.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.UNCOMMON)
            .setBlock(5, 1, 2)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public GreatbladePhalanx()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addBlockMove();
        addUseMove(CCond.combust(), PMultiSkill.join(PMove.gainTemporary(5, PCLPowerHelper.Thorns).setUpgrade(1), PMove.applyToSingle(1, PCLPowerHelper.Vulnerable)));
    }
}

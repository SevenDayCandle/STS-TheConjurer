package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class OceanSubmersion extends PCLCard
{
    public static final PCLCardData DATA = register(OceanSubmersion.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.All)
            .setAffinities(2, PCLAffinity.Blue)
            .setCore();

    public OceanSubmersion()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.applyToEveryone(8, PCLElementHelper.Gelus).setUpgrade(3));
    }
}

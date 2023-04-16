package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class AerosolCoolant extends PCLCard
{
    public static final PCLCardData DATA = register(AerosolCoolant.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.UNCOMMON)
            .setAffinities(1, PCLAffinity.Blue, PCLAffinity.Green)
            .setCore();

    public AerosolCoolant()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PCond.checkPower(PCLCardTarget.All, 0, PCLElementHelper.Aer), PMove.draw(2));
        addUseMove(PMove.applyToSingle(1,  PCLElementHelper.Gelus, PCLElementHelper.Aer).setUpgrade(1));
    }
}

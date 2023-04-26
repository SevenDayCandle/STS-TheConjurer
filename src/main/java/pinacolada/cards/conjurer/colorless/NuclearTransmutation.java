package pinacolada.cards.conjurer.colorless;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class NuclearTransmutation extends PCLCard
{
    public static final PCLCardData DATA = register(NuclearTransmutation.class, ConjurerResources.conjurer)
            .setSkill(-1, CardRarity.RARE, PCLCardTarget.None)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Blue)
            .setCore(true);

    public NuclearTransmutation()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMod.xEnergy(0).setUpgrade(1), PMove.createRandom(1, 1, PCLCardGroupHelper.Hand).edit(f -> f.setType(PCLEnum.CardType.SUMMON)), PMove.modifyCost(-5, 99).useParent(true));
    }
}

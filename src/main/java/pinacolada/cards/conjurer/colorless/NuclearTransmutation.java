package pinacolada.cards.conjurer.colorless;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

public class NuclearTransmutation extends PCLCard
{
    public static final PCLCardData DATA = register(NuclearTransmutation.class, ConjurerResources.conjurer)
            .setSkill(-1, CardRarity.RARE)
            .setTags(PCLCardTag.Exhaust)
            .setUTags(PCLCardTag.Haste, PCLCardTag.Retain)
            .setAffinities(PCLAffinity.Blue)
            .setColorless();

    public NuclearTransmutation()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMod.payXEnergy(), PMove.obtainRandom(1, 1, PCLCardGroupHelper.Hand).setCardTypes(PCLEnum.CardType.SUMMON), PMove.modifyCost(-3));
    }
}

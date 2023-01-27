package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class Aerosol extends PCLCard
{
    public static final PCLCardData DATA = register(Aerosol.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.COMMON)
            .setTags(PCLCardTag.Exhaust.make(), PCLCardTag.Retain.make(0, -1))
            .setAffinities(2, PCLAffinity.Green)
            .setCore();

    public Aerosol()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.draw(1));
        addUseMove(PMove.applyToEnemies(3, PCLElementHelper.Aer));
    }
}

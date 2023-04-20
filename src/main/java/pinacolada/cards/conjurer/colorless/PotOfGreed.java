package pinacolada.cards.conjurer.colorless;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

@VisibleCard
public class PotOfGreed extends PCLCard
{
    public static final PCLCardData DATA = register(PotOfGreed.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.UNCOMMON, PCLCardTarget.None)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Purple)
            .setColorless();

    public PotOfGreed()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PCond.exhaustRandom(2, PCLCardGroupHelper.DrawPile).setUpgrade(-1), PMultiSkill.join(PMove.draw(2).setUpgrade(1), PMove.gain(2, PCLPowerHelper.NextTurnDraw)));
    }
}

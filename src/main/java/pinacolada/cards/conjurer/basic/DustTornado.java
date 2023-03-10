package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class DustTornado extends PCLCard
{
    public static final PCLCardData DATA = register(DustTornado.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.RARE, PCLCardTarget.AllEnemy)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setCore();

    public DustTornado()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PCond.exhaust(1),
                PMove.play(2, PCLCardTarget.SingleAlly, PCLCardGroupHelper.DiscardPile, PCLCardGroupHelper.Hand).setUpgrade(1).edit(f -> f.setType(PCLEnum.CardType.SUMMON).setRandom()));
        addUseMove(PCond.onDiscard(), PMove.applyToEnemies(4, PCLElementHelper.Petra));
    }
}

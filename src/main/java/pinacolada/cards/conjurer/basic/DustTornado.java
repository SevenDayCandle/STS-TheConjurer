package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class DustTornado extends PCLCard
{
    public static final PCLCardData DATA = register(DustTornado.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE, PCLCardTarget.AllEnemy)
            .setRTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setCore();

    public DustTornado()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PCond.exhaust(1), PMove.play(1, PCLCardTarget.SingleAlly, PCLCardGroupHelper.DiscardPile, PCLCardGroupHelper.Hand).setCardTypes(PCLEnum.CardType.SUMMON));
        addUseMove(PCond.onDiscard(), PMove.applyToEnemies(3, PCLElementHelper.Flowed, PCLElementHelper.Stoned));
    }
}

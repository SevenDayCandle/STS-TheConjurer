package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.*;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class DustTornado extends PCLCard
{
    public static final PCLCardData DATA = register(DustTornado.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE, PCLCardTarget.AllEnemy)
            .setCostUpgrades(-1)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setCore();

    public DustTornado()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PCond.exhaust(1),
                PMove.play(2, PCLCardTarget.SingleAlly, PCLCardGroupHelper.DiscardPile, PCLCardGroupHelper.Hand).edit(f -> f.setType(PCLEnum.CardType.SUMMON).setRandom()));
        addUseMove(PCond.onDiscard(), PMove.applyToEnemies(3, PCLElementHelper.Aer, PCLElementHelper.Petra));
    }
}

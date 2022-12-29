package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLAttackType;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

public class FoliarTorque extends PCLCard
{
    public static final PCLCardData DATA = register(FoliarTorque.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON, PCLAttackType.Ranged)
            .setDamage(4, 1)
            .setAffinities(2, PCLAffinity.Green)
            .setCore();

    public FoliarTorque()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.WIND);
        addUseMove(PMod.drawPer(2).setUpgrade(1).setAffinity(PCLAffinity.Green), PMove.applyToRandom(2, PCLElementHelper.Flowed));
    }
}

package pinacolada.cards.conjurer.colorless;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Rathalos extends PCLCard
{
    public static final PCLCardData DATA = register(Rathalos.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.UNCOMMON, PCLAttackType.Normal, PCLCardTarget.RandomEnemy)
            .setDamage(3, 1, 0)
            .setHp(11, 0)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green)
            .setColorless();

    public Rathalos()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.BITE);
        addUseMove(PCond.cooldown(3), PMove.applyToRandom(4, PCLPowerHelper.Poison, PCLPowerHelper.Bruised));
    }
}

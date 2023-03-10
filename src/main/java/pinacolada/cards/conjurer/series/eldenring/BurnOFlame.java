package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class BurnOFlame extends PCLCard
{
    public static final PCLCardData DATA = register(BurnOFlame.class, ConjurerResources.conjurer)
            .setAttack(0, CardRarity.COMMON, PCLAttackType.Ranged)
            .setDamage(2, 3)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public BurnOFlame()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.BURN);
        addUseMove(PMove.applyToSingle(2, PCLElementHelper.Ignis));
    }
}

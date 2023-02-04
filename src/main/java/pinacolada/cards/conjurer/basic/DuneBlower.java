package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class DuneBlower extends PCLCard
{
    public static final PCLCardData DATA = register(DuneBlower.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON, PCLAttackType.Ranged)
            .setDamage(2, 2, 2)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setCore();

    public DuneBlower()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.SLASH_HORIZONTAL);
        addUseMove(PMod.discardPer(2), PMove.applyToSingle(2, PCLElementHelper.Aer, PCLElementHelper.Petra));
    }
}

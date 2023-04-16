package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrait;

@VisibleCard
public class GiantsflameTakeThee extends PCLCard
{
    public static final PCLCardData DATA = register(GiantsflameTakeThee.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON, PCLAttackType.Ranged)
            .setDamage(14, 2)
            .setAffinities(2, PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public GiantsflameTakeThee()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.SMALL_EXPLOSION).setChain(PCond.checkPower(PCLCardTarget.Single, 1, PCLElementHelper.Frostbite), PTrait.damage(7).setUpgrade(3));
        addUseMove(PMove.applyToSingle(3, PCLElementHelper.Ignis));
    }
}

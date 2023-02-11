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
import pinacolada.skills.skills.PMultiCond;

@VisibleCard
public class GiantsflameTakeThee extends PCLCard
{
    public static final PCLCardData DATA = register(GiantsflameTakeThee.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON, PCLAttackType.Ranged)
            .setDamage(18, 3)
            .setAffinities(2, PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public GiantsflameTakeThee()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.SMALL_EXPLOSION);
        addUseMove(PMultiCond.ifElse(PMove.gainBlock(10), PMove.applyToSingle(3, PCLElementHelper.Ignis), PCond.checkPower(PCLCardTarget.Single, 9, PCLElementHelper.Frostbite)));
    }
}

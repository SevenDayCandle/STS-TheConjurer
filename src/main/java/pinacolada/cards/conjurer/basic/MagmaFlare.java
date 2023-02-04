package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class MagmaFlare extends PCLCard
{
    public static final PCLCardData DATA = register(MagmaFlare.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.COMMON, PCLAttackType.Ranged, PCLCardTarget.AllEnemy)
            .setDamage(13, 2)
            .setAffinities(PCLAffinity.Red)
            .setCore();

    public MagmaFlare()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.SMALL_EXPLOSION);
        addUseMove(PMove.applyToEnemies(2, PCLElementHelper.Ignis).setUpgrade(1));
    }
}

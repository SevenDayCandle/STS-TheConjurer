package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.EffekseerEFK;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class RagingInferno extends PCLCard
{
    public static final PCLCardData DATA = register(RagingInferno.class, ConjurerResources.conjurer)
            .setAttack(3, CardRarity.RARE, PCLAttackType.Normal)
            .setTags(PCLCardTag.Exhaust)
            .setDamage(34, 4)
            .setAffinities(2, PCLAffinity.Red)
            .setCore();

    public RagingInferno()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(EffekseerEFK.FIRE15);
        addUseMove(PMove.applyToSingle(8, PCLElementHelper.Ignis).setUpgrade(1));
    }
}

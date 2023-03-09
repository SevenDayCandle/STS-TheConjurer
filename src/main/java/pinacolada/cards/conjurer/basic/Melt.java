package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.EffekseerEFK;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Melt extends PCLCard
{
    public static final PCLCardData DATA = register(Melt.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.RARE, PCLAttackType.Magical)
            .setDamage(10, 1)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue)
            .setMaxCopies(2)
            .setCore();

    public Melt()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove().setDamageEffect(EffekseerEFK.FIRE07);
        addUseMove(CCond.combust(), PMove.loseHpPercent(PCLCardTarget.Single, 20).setUpgrade(5));
    }
}

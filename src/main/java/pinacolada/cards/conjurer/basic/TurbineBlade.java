package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMod;
import pinacolada.skills.PMove;

@VisibleCard
public class TurbineBlade extends PCLCard
{
    public static final PCLCardData DATA = register(TurbineBlade.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON)
            .setDamage(5, 3)
            .setAffinities(PCLAffinity.Green)
            .setCore();

    public TurbineBlade()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.SLASH_HORIZONTAL);
        addUseMove(CMod.bonusOnRedox(1), PMove.draw(2).edit(f -> f.setAffinity(PCLAffinity.Green)));
    }
}

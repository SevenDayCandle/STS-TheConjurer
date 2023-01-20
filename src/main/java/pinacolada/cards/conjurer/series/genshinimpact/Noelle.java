package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Noelle extends PCLCard
{
    public static final PCLCardData DATA = register(Noelle.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.COMMON)
            .setDamage(2, 1)
            .setPriority(1)
            .setHp(7, 2)
            .setAffinities(PCLAffinity.Orange, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Noelle()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.SLASH_HORIZONTAL);
        addUseMove(PCond.cooldown(0), PMove.gainBlock(PCLCardTarget.Team, 2));
    }
}

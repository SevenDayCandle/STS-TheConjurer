package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Noelle extends PCLCard
{
    public static final PCLCardData DATA = register(Noelle.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.COMMON)
            .setDamage(2, 0)
            .setPriority(1)
            .setHp(7, 3)
            .setAffinities(PCLAffinity.Orange, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Noelle()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.SLASH_HORIZONTAL);
        addUseMove(PCond.cooldown(0), PMove.gainBlock(PCLCardTarget.Team, 2).setUpgrade(1));
    }
}

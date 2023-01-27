package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Bennett extends PCLCard
{
    public static final PCLCardData DATA = register(Bennett.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.COMMON)
            .setDamage(4, 0)
            .setPriority(1)
            .setHp(5, 2)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Bennett()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.BLUNT_LIGHT);
        addUseMove(PCond.cooldown(1), PMove.apply(PCLCardTarget.Team, 3, PCLPowerHelper.Vigor).setUpgrade(1));
    }
}

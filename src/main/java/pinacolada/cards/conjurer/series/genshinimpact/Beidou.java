package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

public class Beidou extends PCLCard
{
    public static final PCLCardData DATA = register(Beidou.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.COMMON)
            .setDamage(3, 1)
            .setPriority(1)
            .setHp(12, 2)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Beidou()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.SLASH_HEAVY);
        addUseMove(PCond.cooldown(2), PMultiSkill.join(PMove.dealDamageToAll(3), PMove.gainBlock(PCLCardTarget.Team, 5)));
    }
}

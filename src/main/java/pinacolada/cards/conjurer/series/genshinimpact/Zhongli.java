package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.*;
import pinacolada.cards.conjurer.basic.ErodingTerra;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class Zhongli extends PCLCard
{
    public static final PCLCardData DATA = register(Zhongli.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.RARE, PCLAttackType.Piercing)
            .setDamage(2, 0)
            .setPriority(1)
            .setHp(25, 3)
            .setAffinities(PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Zhongli()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.EARTH);
        addUseMove(PCond.cooldown(6).setUpgrade(-1), PMove.playCopy(1, PCLCardTarget.None, ErodingTerra.DATA.ID));
    }
}

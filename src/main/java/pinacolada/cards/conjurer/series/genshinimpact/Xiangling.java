package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.*;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class Xiangling extends PCLCard
{
    public static final PCLCardData DATA = register(Xiangling.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.COMMON, PCLAttackType.Normal, PCLCardTarget.AllEnemy)
            .setDamage(3, 1)
            .setPriority(1)
            .setHp(6, 2)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Xiangling()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.SLASH_HORIZONTAL);
        addUseMove(PCond.cooldown(0), PMove.applyToRandom(3, PCLElementHelper.Ignis));
    }
}

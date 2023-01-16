package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.*;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PTrait;

public class Tartaglia extends PCLCard
{
    public static final PCLCardData DATA = register(Tartaglia.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.COMMON, PCLAttackType.Normal, PCLCardTarget.AllEnemy)
            .setDamage(1, 1, 2)
            .setPriority(1)
            .setHp(7, 2)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Tartaglia()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.SLASH_HORIZONTAL);
        addUseMove(PMod.perCreatureWith(1, PCLElementHelper.Ignis), PTrait.hasDamage(1));
    }
}

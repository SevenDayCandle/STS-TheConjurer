package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLAttackType;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class Gorou extends PCLCard
{
    public static final PCLCardData DATA = register(Gorou.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.COMMON, PCLAttackType.Ranged)
            .setDamage(4, 1)
            .setPriority(1)
            .setHp(6, 2)
            .setAffinities(PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Gorou()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.BLUNT_LIGHT);
        addUseMove(PCond.cooldown(0), PMove.applyToSingle(2, PCLElementHelper.Petra));
    }
}

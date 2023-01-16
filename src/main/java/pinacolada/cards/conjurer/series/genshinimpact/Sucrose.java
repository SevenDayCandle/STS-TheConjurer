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

public class Sucrose extends PCLCard
{
    public static final PCLCardData DATA = register(Sucrose.class, ConjurerResources.conjurer)
            .setSummon(0, CardRarity.COMMON, PCLAttackType.Magical)
            .setDamage(2, 1)
            .setPriority(1)
            .setHp(3, 2)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Sucrose()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.WIND);
        addUseMove(PCond.cooldown(0), PMove.applyToSingle(2, PCLElementHelper.Aer));
    }
}

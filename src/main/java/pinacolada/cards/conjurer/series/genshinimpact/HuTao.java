package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class HuTao extends PCLCard
{
    public static final PCLCardData DATA = register(HuTao.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Piercing, PCLCardTarget.RandomEnemy)
            .setRTags(PCLCardTag.Ethereal)
            .setDamage(3, 0)
            .setPriority(1)
            .setHp(6, 2)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public HuTao()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.FIRE);
        addUseMove(PCond.cooldown(0), PCond.exhaustRandom(1, PCLCardGroupHelper.Hand), PMove.gainEnergy(1));
    }
}

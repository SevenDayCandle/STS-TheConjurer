package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.*;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class Xiangling extends PCLCard
{
    public static final PCLCardData DATA = register(Xiangling.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON, PCLAttackType.Normal, PCLCardTarget.AllEnemy)
            .setDamage(5, array(3, 0))
            .setAffinities(PCLAffinity.Red)
            .setMultiformData(2)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Xiangling()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.SLASH_DIAGONAL);
        addUseMove(PMove.obtainDiscardPile(1, Xiangling_Guoba.DATA).setUpgrade(0, 1));
        addUseMove(PCond.onDraw(), PMove.applyToRandom(2, PCLPowerHelper.Burning));
    }
}

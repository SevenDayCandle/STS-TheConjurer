package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.*;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.skills.PTrigger;

public class Diluc extends PCLCard
{
    public static final PCLCardData DATA = register(Diluc.class, ConjurerResources.conjurer)
            .setAttack(3, CardRarity.UNCOMMON, PCLAttackType.Normal, PCLCardTarget.AllEnemy)
            .setDamage(15, 3)
            .setAffinities(2, PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Diluc()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove().setDamageEffect(PCLEffekseerEFX.BLOW03);
        addGainPower(2, PTrigger.when(PCond.delegate(PSkill.Delegate.Discard, PSkill.Delegate.Exhaust), PMove.applyToEnemies(1, PCLPowerHelper.Burning)));
        addUseMove(PCond.redox(), PMove.gain(5, PCLPowerHelper.NextTurnBlock));
    }
}

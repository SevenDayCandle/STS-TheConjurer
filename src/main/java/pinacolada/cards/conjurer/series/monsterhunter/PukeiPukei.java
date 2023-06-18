package pinacolada.cards.conjurer.series.monsterhunter;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.delay.DelayTiming;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class PukeiPukei extends PCLCard {
    public static final PCLCardData DATA = register(PukeiPukei.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.COMMON, PCLAttackType.Ranged, PCLCardTarget.RandomEnemy, DelayTiming.EndOfTurnFirst)
            .setDamage(3, 1, 0)
            .setHp(7, 0)
            .setAffinities(PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public PukeiPukei() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.POISON);
        addGainPower(PTrigger.when(PCond.haveTakenDamage(), PMove.applyToEnemies(4, PCLPowerHelper.Poison)));
    }
}

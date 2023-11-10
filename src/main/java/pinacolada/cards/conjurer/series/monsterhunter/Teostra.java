package pinacolada.cards.conjurer.series.monsterhunter;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.delay.DelayTiming;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Teostra extends PCLCard {
    public static final PCLCardData DATA = register(Teostra.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.RARE, PCLAttackType.Normal, PCLCardTarget.AllEnemy, DelayTiming.EndOfTurnFirst)
            .setDamage(4, 0)
            .setHp(14, 2)
            .setAffinities(2, PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public Teostra() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SMALL_EXPLOSION);
        addGainPower(PTrigger.when(PCond.onAllyTrigger(), PMove.applyToEnemies(1, IgnisPower.DATA).setUpgrade(1)));
    }
}

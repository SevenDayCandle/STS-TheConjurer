package pinacolada.cards.conjurer.series.monsterhunter;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.delay.DelayTiming;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Bazelgeuse extends PCLCard {
    public static final PCLCardData DATA = register(Bazelgeuse.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.UNCOMMON, PCLAttackType.Ranged, PCLCardTarget.AllEnemy, DelayTiming.EndOfTurnFirst)
            .setDamage(4, 0)
            .setHp(12, 2)
            .setAffinities(1, PCLAffinity.Red, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public Bazelgeuse() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SMALL_EXPLOSION);
        addGainPower(PTrigger.when(PCond.checkDamage(PCLCardTarget.None, 1), PMove.applyToRandom(2, BlastedPower.DATA).setUpgrade(1)));
    }
}

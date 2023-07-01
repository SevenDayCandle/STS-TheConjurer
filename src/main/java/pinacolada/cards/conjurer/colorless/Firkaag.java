package pinacolada.cards.conjurer.colorless;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.delay.DelayTiming;
import pinacolada.skills.PTrait;

@VisibleCard
public class Firkaag extends PCLCard {
    public static final PCLCardData DATA = register(Firkaag.class, ConjurerResources.conjurer)
            .setSummon(4, CardRarity.RARE, PCLAttackType.Piercing, PCLCardTarget.Single, DelayTiming.EndOfTurnFirst)
            .setDamage(5, 0)
            .setHp(21, 5)
            .setAffinities(1, PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.baldursGate, true);

    public Firkaag() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.FIRE).setBonus(PMod.perCardPlayed(1).setExtra(20), 2, 1);
        addUseMove(PMod.perCardPlayedCombat(12), PTrait.cost(-1));
    }
}

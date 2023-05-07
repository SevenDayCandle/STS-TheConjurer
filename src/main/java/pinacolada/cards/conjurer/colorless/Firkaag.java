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
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PTrait;
import pinacolada.skills.skills.DelayTiming;

@VisibleCard
public class Firkaag extends PCLCard {
    public static final PCLCardData DATA = register(Firkaag.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.RARE, PCLAttackType.Piercing, PCLCardTarget.AllEnemy, DelayTiming.EndOfTurnFirst)
            .setDamage(13, 0)
            .setHp(21, 5)
            .setAffinities(1, PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.touhouProject, true);

    public Firkaag() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.FIRE).setBonus(PMod.perCardPlayed(1), 3, 1);
        addUseMove(PCond.havePlayed(35).edit(f -> f.setNot(true).setForced(true)), PTrait.unplayable());
    }
}

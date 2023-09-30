package pinacolada.cards.conjurer.series.monsterhunter;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.PetraPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.delay.DelayTiming;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class SharaIshvalda extends PCLCard {
    public static final PCLCardData DATA = register(SharaIshvalda.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.RARE, PCLAttackType.Piercing, PCLCardTarget.AllEnemy, DelayTiming.EndOfTurnFirst)
            .setDamage(5, 1)
            .setHp(15, 1)
            .setAffinities(2, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public SharaIshvalda() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.EARTH);
        addGainPower(PTrigger.when(PCond.onDiscard(), PMove.applyToTeam(1, PetraPower.DATA).setUpgrade(1)));
    }
}

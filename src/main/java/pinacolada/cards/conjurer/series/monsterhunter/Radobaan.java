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
public class Radobaan extends PCLCard {
    public static final PCLCardData DATA = register(Radobaan.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.COMMON, PCLAttackType.Normal, PCLCardTarget.Single, DelayTiming.EndOfTurnFirst)
            .setDamage(3, 1, 0)
            .setHp(15, 2)
            .setAffinities(2, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public Radobaan() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.BITE);
        addGainPower(PTrigger.when(PCond.haveTakenDamage(), PMove.gain(3, PCLPowerHelper.Thorns)));
    }
}

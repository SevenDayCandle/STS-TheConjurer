package pinacolada.cards.conjurer.series.honkai;


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
public class Blade extends PCLCard {
    public static final PCLCardData DATA = register(Blade.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.RARE, PCLAttackType.Piercing, PCLCardTarget.Single, DelayTiming.EndOfTurnFirst)
            .setDamage(6, 1)
            .setHp(12, 1)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.honkai);

    public Blade() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SLASH_HORIZONTAL);
        addGainPower(PTrigger.when(PCond.haveTakenDamage(), PMove.applyToAllies(2, PCLPowerHelper.Vigor)));
    }
}

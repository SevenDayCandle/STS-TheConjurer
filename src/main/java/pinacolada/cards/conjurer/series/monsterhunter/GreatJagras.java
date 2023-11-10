package pinacolada.cards.conjurer.series.monsterhunter;


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
import pinacolada.skills.PMove;
import pinacolada.skills.delay.DelayTiming;

@VisibleCard
public class GreatJagras extends PCLCard {
    public static final PCLCardData DATA = register(GreatJagras.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.COMMON, PCLAttackType.Normal, PCLCardTarget.Single, DelayTiming.EndOfTurnFirst)
            .setDamage(5, 2)
            .setHp(6, 0)
            .setAffinities(PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public GreatJagras() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.CLAW);
        addUseMove(PCond.onDeath(), PMove.gainTempHP(PCLCardTarget.None, 12).setUpgrade(3));
    }
}

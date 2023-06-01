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
import pinacolada.skills.PMove;
import pinacolada.skills.skills.DelayTiming;

@VisibleCard
public class Diablos extends PCLCard {
    public static final PCLCardData DATA = register(Diablos.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.UNCOMMON, PCLAttackType.Normal, PCLCardTarget.RandomEnemy, DelayTiming.EndOfTurnFirst)
            .setDamage(4, 1, 0)
            .setHp(17, 0)
            .setAffinities(2, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.monsterHunter, true);

    public Diablos() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.EARTH);
        addUseMove(PCond.cooldown(3), PMove.discardRandom(3), PMove.gainBlockPlayer(16));
    }
}

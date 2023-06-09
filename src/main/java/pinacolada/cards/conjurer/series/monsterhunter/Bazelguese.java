package pinacolada.cards.conjurer.series.monsterhunter;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.DelayTiming;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Bazelguese extends PCLCard {
    public static final PCLCardData DATA = register(Bazelguese.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.RARE, PCLAttackType.Ranged, PCLCardTarget.AllEnemy, DelayTiming.EndOfTurnFirst)
            .setDamage(4, 1)
            .setHp(10, 1)
            .setAffinities(2, PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public Bazelguese() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SMALL_EXPLOSION);
        addGainPower(PTrigger.when(PCond.onAllyTrigger(), PMove.applyToEnemies(1, PCLElementHelper.Blasted)));
    }
}

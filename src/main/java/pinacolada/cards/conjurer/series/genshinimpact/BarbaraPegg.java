package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.conditions.PCond_OnAllyDeath;

@VisibleCard
public class BarbaraPegg extends PCLCard {
    public static final PCLCardData DATA = register(BarbaraPegg.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Immaterial, PCLCardTarget.AllEnemy)
            .setDamage(1, 2)
            .setHp(3, 0)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public BarbaraPegg() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.WATER);
        addUseMove(new PCond_OnAllyDeath(), PMove.heal(PCLCardTarget.Team, 8));
    }
}

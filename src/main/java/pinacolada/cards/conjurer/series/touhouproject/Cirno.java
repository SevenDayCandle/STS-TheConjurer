package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Cirno extends PCLCard {
    public static final PCLCardData DATA = register(Cirno.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.COMMON, PCLAttackType.Immaterial, PCLCardTarget.AllEnemy)
            .setDamage(3, 1)
            .setHp(3, 1)
            .setAffinities(1, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public Cirno() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.ICE);
        addUseMove(PCond.cooldown(2), PMove.applyToEnemies(3, PCLElementHelper.Aqua, PCLPowerHelper.Shackles));
    }
}

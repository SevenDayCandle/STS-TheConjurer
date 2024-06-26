package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class RykardsRancor extends PCLCard {
    public static final PCLCardData DATA = register(RykardsRancor.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.RARE, PCLAttackType.Ranged, PCLCardTarget.AllEnemy)
            .setRTags(PCLCardTag.Ethereal)
            .setDamage(23, 0)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public RykardsRancor() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(ConjurerEFK.FIRE16);
        addUseMove(PCond.exhaustRandom(1), PMove.applyToEnemies(1, PCLPowerData.Vulnerable));
    }
}

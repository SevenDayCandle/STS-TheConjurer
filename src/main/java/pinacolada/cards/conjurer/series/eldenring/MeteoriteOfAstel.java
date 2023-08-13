package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PDelay;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

@VisibleCard
public class MeteoriteOfAstel extends PCLCard {
    public static final PCLCardData DATA = register(MeteoriteOfAstel.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.RARE, PCLAttackType.Ranged, PCLCardTarget.AllEnemy)
            .setDamage(5, 2)
            .setAffinities(1, PCLAffinity.Orange, PCLAffinity.Purple)
            .setTags(PCLCardTag.Exhaust)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public MeteoriteOfAstel() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.EARTH);
        addUseMove(PDelay.turnStart(3), PMultiSkill.join(PMove.dealDamageToAll(40, PCLAttackVFX.EARTH.key).setUpgrade(8), PMove.applyToEnemies(7, PCLElementHelper.Petra)));
    }
}

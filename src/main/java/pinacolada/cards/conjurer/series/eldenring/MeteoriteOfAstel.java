package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.ConjurerEFK;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.ForgingPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PDelay;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class MeteoriteOfAstel extends PCLCard {
    public static final PCLCardData DATA = register(MeteoriteOfAstel.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.RARE, PCLAttackType.Ranged, PCLCardTarget.AllEnemy)
            .setDamage(7, 3)
            .setAffinities(1, PCLAffinity.Orange, PCLAffinity.Purple)
            .setTags(PCLCardTag.Exhaust)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public MeteoriteOfAstel() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.EARTH);
        addUseMove(PDelay.turnStart(3), PMod.perPowerSelf(1, ForgingPower.DATA), PMove.dealDamageToAll(7).setVFX(ConjurerEFK.BLOW02).setUpgrade(2));
    }
}

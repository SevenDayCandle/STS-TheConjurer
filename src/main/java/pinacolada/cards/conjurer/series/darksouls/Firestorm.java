package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.EffekseerEFK;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class Firestorm extends PCLCard {
    public static final PCLCardData DATA = register(Firestorm.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON, PCLAttackType.Ranged, PCLCardTarget.AllEnemy)
            .setDamage(10, 5)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public Firestorm() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.SWORD04);
        addUseMove(PMod.payPerPower(IgnisPower.DATA).setTarget(PCLCardTarget.All), PMove.applyToEnemies(2, BlastedPower.DATA).setUpgrade(1));
    }
}

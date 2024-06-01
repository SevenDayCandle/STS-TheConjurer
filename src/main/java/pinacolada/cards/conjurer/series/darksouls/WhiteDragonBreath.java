package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.powers.conjurer.CooledPower;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.moves.PMove_StabilizePower;

@VisibleCard
public class WhiteDragonBreath extends PCLCard {
    public static final PCLCardData DATA = register(WhiteDragonBreath.class, ConjurerResources.conjurer)
            .setAttack(3, CardRarity.RARE, PCLAttackType.Immaterial)
            .setDamage(23, 5)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(1, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public WhiteDragonBreath() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(ConjurerEFK.MGC_W2_SuperSphereAttack);
        addUseMove(PMod.payPerPower(IgnisPower.DATA).setTarget(PCLCardTarget.Single), PMove.applyToEnemies(3, CooledPower.DATA).setUpgrade(1));
    }
}

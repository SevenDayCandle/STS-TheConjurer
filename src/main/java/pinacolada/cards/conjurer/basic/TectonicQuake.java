package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.conjurer.PetraPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.modifiers.PMod_PayPerPower;

@VisibleCard
public class TectonicQuake extends PCLCard {
    public static final PCLCardData DATA = register(TectonicQuake.class, ConjurerResources.conjurer)
            .setAttack(3, CardRarity.RARE, PCLAttackType.Normal, PCLCardTarget.AllEnemy)
            .setTags(PCLCardTag.Exhaust)
            .setDamage(23, 5)
            .setAffinities(2, PCLAffinity.Orange)
            .setCore();

    public TectonicQuake() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove().setDamageEffect(ConjurerEFK.MGC_EarthSpell_LV3);
        addUseMove(PMod.payPerPower(PetraPower.DATA).setTarget(PCLCardTarget.All), PMove.gainBlock(3).setUpgrade(1));
    }
}

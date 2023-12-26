package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Condensation extends PCLCard {
    public static final PCLCardData DATA = register(Condensation.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.COMMON)
            .setAffinities(PCLAffinity.Blue.make(1, 1))
            .setCore();

    public Condensation() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.applyToSingle(1, AquaPower.DATA, PCLPowerData.Weak).setVFX(ConjurerEFK.MGC_HealingSpell_LV1));
        addUseMove(PCond.checkAlly(PCLCardTarget.Single), PMove.triggerAlly(PCLCardTarget.UseParent, 2).setUpgrade(1));
    }
}

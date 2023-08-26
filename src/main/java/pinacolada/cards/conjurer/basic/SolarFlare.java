package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.modifiers.PMod_PerCreatureDamage;

@VisibleCard
public class SolarFlare extends PCLCard {
    public static final PCLCardData DATA = register(SolarFlare.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Single)
            .setBlock(8, 1)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Yellow)
            .setCore();

    public SolarFlare() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
        addUseMove(new PMod_PerCreatureDamage(PCLCardTarget.Single, 3), PMove.applyToSingle(1, PCLElementHelper.Ignis));
    }
}

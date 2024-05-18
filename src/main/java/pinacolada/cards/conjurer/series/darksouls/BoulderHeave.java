package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.conjurer.PetraPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class BoulderHeave extends PCLCard {
    public static final PCLCardData DATA = register(BoulderHeave.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.COMMON, PCLCardTarget.Single)
            .setBlock(13, 4)
            .setAffinities(2, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public BoulderHeave() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
        addUseMove(PMove.applyToSingle(1, PetraPower.DATA), PMod.increaseOnUse(2));
    }
}

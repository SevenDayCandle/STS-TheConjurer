package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMod;
import pinacolada.skills.PMove;

@VisibleCard
public class PoisonMist extends PCLCard {
    public static final PCLCardData DATA = register(PoisonMist.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON, PCLCardTarget.AllEnemy)
            .setAffinities(PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public PoisonMist() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(CMod.bonusOnReact(2), PMove.applyToEnemies(2, PCLElementHelper.Ventus, PCLPowerHelper.Poison).setUpgrade(2));
    }
}

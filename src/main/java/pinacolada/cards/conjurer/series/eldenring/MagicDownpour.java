package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PDelay;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class MagicDownpour extends PCLCard {
    public static final PCLCardData DATA = register(MagicDownpour.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.RARE, PCLCardTarget.Team)
            .setAffinities(2, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public MagicDownpour() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMod.bonusPerLevel(2, PCLAffinity.Blue), PMove.gainTempHP(8).setVFX(ConjurerEFK.CURE02).setUpgrade(2));
        addUseMove(PDelay.turnStart(1), PMove.applyToEveryone(8, PCLElementHelper.Aqua).setUpgrade(1));
    }
}

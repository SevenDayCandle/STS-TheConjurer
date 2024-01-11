package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMod;
import pinacolada.skills.PDelay;
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
        addUseMove(PMove.gainTempHP(14).setVFX(ConjurerEFK.CURE02).setUpgrade(2));
        addUseMove(PDelay.turnStart(1), PMove.applyToEveryone(3, AquaPower.DATA).setUpgrade(1));
    }
}

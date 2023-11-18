package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.powers.conjurer.PetraPower;
import pinacolada.powers.conjurer.VentusPower;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class Paintball extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(Paintball.class, ConjurerResources.conjurer)
            .setProps(RelicTier.UNCOMMON, LandingSound.MAGICAL)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public Paintball() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.interactablePerCombat(3, PMove.applyToRandom(1, IgnisPower.DATA, AquaPower.DATA, VentusPower.DATA, PetraPower.DATA)));
    }
}
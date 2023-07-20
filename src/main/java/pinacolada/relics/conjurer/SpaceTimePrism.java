package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.special.primary.PRoot;

@VisibleRelic
public class SpaceTimePrism extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(SpaceTimePrism.class, ConjurerResources.conjurer)
            .setProps(RelicTier.UNCOMMON, LandingSound.MAGICAL);

    public SpaceTimePrism() {
        super(DATA);
    }

    public void setup() {
        addUseMove(new PRoot(), PMove.discardRandom(9, PCLCardGroupHelper.DrawPile), PMove.upgrade(9).useParent(true));
    }
}
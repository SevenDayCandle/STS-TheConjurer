package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLCardSelection;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.special.primary.PRoot;

@VisibleRelic
public class MusketeersFeltHat extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(MusketeersFeltHat.class, ConjurerResources.conjurer)
            .setProps(RelicTier.COMMON, LandingSound.FLAT)
            .setLoadout(ConjurerPlayerData.honkai);

    public MusketeersFeltHat() {
        super(DATA);
    }

    public void setup() {
        addUseMove(new PRoot(), PMove.play(1, PCLCardTarget.RandomEnemy, PCLCardGroupHelper.DrawPile).edit(f -> f.setOrigin(PCLCardSelection.Top)));
    }
}
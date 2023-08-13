package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.skills.special.primary.PRoot;

@VisibleRelic
public class YasakaMagatama extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(YasakaMagatama.class, ConjurerResources.conjurer)
            .setProps(RelicTier.COMMON, LandingSound.MAGICAL);

    public YasakaMagatama() {
        super(DATA);
    }

    public void setup() {
        addUseMove(new PRoot(), CMove.gainMatter(20).setUpgrade(10));
    }
}
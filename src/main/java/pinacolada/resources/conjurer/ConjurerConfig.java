package pinacolada.resources.conjurer;

import extendedui.configuration.STSConfigItem;
import pinacolada.resources.PCLCharacterConfig;
import pinacolada.resources.PCLMainConfig;

public class ConjurerConfig extends PCLCharacterConfig {
    private static final String MOD_ID = "CONJURER";
    private static final String SEEN_SUMMON_TUTORIAL_CONJURER = PCLMainConfig.createFullID("SeenSummonTutorialConjurer");

    public STSConfigItem<Boolean> seenSummonTutorial;

    public ConjurerConfig() {
        super(MOD_ID);
        this.seenSummonTutorial = new STSConfigItem<Boolean>(SEEN_SUMMON_TUTORIAL_CONJURER, false);
    }

    public void loadImpl() {
        super.loadImpl();
        this.seenSummonTutorial.addConfig(this.config);
    }

    public void resetTutorial() {
        super.resetTutorial();
        this.seenSummonTutorial.set(false);
    }
}

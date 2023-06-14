package pinacolada.resources.conjurer;

import extendedui.configuration.STSConfigItem;
import pinacolada.resources.PCLCharacterConfig;
import pinacolada.resources.PCLMainConfig;

public class ConjurerConfig extends PCLCharacterConfig {
    private static final String MOD_ID = "Conjurer";
    private static final String BANNED_CARDS_CONJURER = PCLMainConfig.createFullID("BannedCardsConjurer");
    private static final String BANNED_RELICS_CONJURER = PCLMainConfig.createFullID("BannedRelicsConjurer");
    private static final String CARDS_COUNT_CONJURER = PCLMainConfig.createFullID("CardsCountConjurer");
    private static final String METER_POSITION_CONJURER = PCLMainConfig.createFullID("MeterPositionConjurer");
    private static final String SEEN_TUTORIAL_CONJURER = PCLMainConfig.createFullID("SeenTutorialConjurer");
    private static final String SEEN_SUMMON_TUTORIAL_CONJURER = PCLMainConfig.createFullID("SeenSummonTutorialConjurer");
    private static final String TROPHIES_CONJURER = PCLMainConfig.createFullID("TrophiesConjurer");

    public STSConfigItem<Boolean> seenSummonTutorial;

    public ConjurerConfig() {
        super(MOD_ID, BANNED_CARDS_CONJURER, BANNED_RELICS_CONJURER, CARDS_COUNT_CONJURER, METER_POSITION_CONJURER, SEEN_TUTORIAL_CONJURER, TROPHIES_CONJURER);
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

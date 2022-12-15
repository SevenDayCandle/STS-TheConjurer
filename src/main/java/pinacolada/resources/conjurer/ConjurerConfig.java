package pinacolada.resources.conjurer;

import com.badlogic.gdx.math.Vector2;
import extendedui.configuration.STSConfigItem;
import extendedui.configuration.STSSerializedConfigItem;
import extendedui.configuration.STSStringConfigItem;
import pinacolada.resources.AbstractConfig;
import pinacolada.resources.pcl.PCLCoreConfig;

import java.util.HashSet;

import static pinacolada.ui.characterSelection.PCLLoadoutsContainer.MINIMUM_CARDS;

public class ConjurerConfig extends AbstractConfig
{
    private static final String MOD_ID = "Conjurer";
    private static final String BANNED_CARDS_CONJURER = PCLCoreConfig.createFullID("BannedCardsConjurer");
    private static final String BANNED_RELICS_CONJURER = PCLCoreConfig.createFullID("BannedRelicsConjurer");
    private static final String CARDS_COUNT_CONJURER = PCLCoreConfig.createFullID("CardsCountConjurer");
    private static final String CONJURER_METER_POSITION = PCLCoreConfig.createFullID("ConjurerMeterPosition");
    private static final String TROPHIES_CONJURER = PCLCoreConfig.createFullID("TrophiesConjurer");
    public STSSerializedConfigItem<HashSet<String>> bannedCardsConjurer = new STSSerializedConfigItem<HashSet<String>>(BANNED_CARDS_CONJURER, new HashSet<>());
    public STSSerializedConfigItem<HashSet<String>> bannedRelicsConjurer = new STSSerializedConfigItem<HashSet<String>>(BANNED_RELICS_CONJURER, new HashSet<>());
    public STSConfigItem<Integer> cardsCountConjurer = new STSConfigItem<Integer>(CARDS_COUNT_CONJURER, MINIMUM_CARDS);
    public STSSerializedConfigItem<Vector2> conjurerMeterPosition = new STSSerializedConfigItem<Vector2>(CONJURER_METER_POSITION, new Vector2(0.35f, 0.8f));
    public STSStringConfigItem trophiesConjurer = new STSStringConfigItem(TROPHIES_CONJURER, "");

    public ConjurerConfig()
    {
        super(MOD_ID);
    }

    @Override
    public void loadImpl()
    {
        bannedCardsConjurer.addConfig(config);
        bannedRelicsConjurer.addConfig(config);
        cardsCountConjurer.addConfig(config);
        conjurerMeterPosition.addConfig(config);
        trophiesConjurer.addConfig(config);
    }
}

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

    public ConjurerConfig()
    {
        super(MOD_ID);
        bannedCards = new STSSerializedConfigItem<HashSet<String>>(BANNED_CARDS_CONJURER, new HashSet<>());
        bannedRelics = new STSSerializedConfigItem<HashSet<String>>(BANNED_RELICS_CONJURER, new HashSet<>());
        cardsCount = new STSConfigItem<Integer>(CARDS_COUNT_CONJURER, MINIMUM_CARDS);
        meterPosition = new STSSerializedConfigItem<Vector2>(CONJURER_METER_POSITION, new Vector2(0.35f, 0.8f));
        trophies = new STSStringConfigItem(TROPHIES_CONJURER, "");
    }

    @Override
    public void loadImpl()
    {
        bannedCards.addConfig(config);
        bannedRelics.addConfig(config);
        cardsCount.addConfig(config);
        meterPosition.addConfig(config);
        trophies.addConfig(config);
    }
}

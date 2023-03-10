package pinacolada.resources.conjurer;

import com.megacrit.cardcrawl.relics.*;
import extendedui.EUIUtils;
import pinacolada.relics.conjurer.PeriodicTable;
import pinacolada.resources.*;
import pinacolada.resources.loadout.PCLLoadout;

import java.util.List;

public class ConjurerPlayerData extends PCLAbstractPlayerData
{
    private static final String MOD_ID = "Conjurer";
    private static final String BANNED_CARDS_CONJURER = PCLMainConfig.createFullID("BannedCardsConjurer");
    private static final String BANNED_RELICS_CONJURER = PCLMainConfig.createFullID("BannedRelicsConjurer");
    private static final String CARDS_COUNT_CONJURER = PCLMainConfig.createFullID("CardsCountConjurer");
    private static final String CONJURER_METER_POSITION = PCLMainConfig.createFullID("ConjurerMeterPosition");
    private static final String TROPHIES_CONJURER = PCLMainConfig.createFullID("TrophiesConjurer");

    public static ConjurerLoadout core = new ConjurerLoadout(-1, 0);
    public static ConjurerLoadout genshinImpact = new ConjurerLoadout(0, 0);
    public static ConjurerLoadout shinMegamiTensei = new ConjurerLoadout(1, 0);
    public static ConjurerLoadout touhouProject = new ConjurerLoadout(2, 0);
    public static ConjurerLoadout eldenRing = new ConjurerLoadout(3, 0);

    public ConjurerPlayerData(PCLResources<?, ?, ?> resources)
    {
        super(resources);
    }

    @Override
    public List<PCLLoadout> getAvailableLoadouts()
    {
        return EUIUtils.list(
                genshinImpact,
                shinMegamiTensei,
                eldenRing
        );
    }

    @Override
    public PCLCharacterConfig getConfig()
    {
        return new PCLCharacterConfig(MOD_ID, BANNED_CARDS_CONJURER, BANNED_RELICS_CONJURER, CARDS_COUNT_CONJURER, CONJURER_METER_POSITION, TROPHIES_CONJURER);
    }

    public void updateRelicsForDungeon()
    {
        PGR.core.dungeon.removeRelic(PenNib.ID);
        PGR.core.dungeon.removeRelic(Kunai.ID);
        PGR.core.dungeon.removeRelic(Shuriken.ID);
        PGR.core.dungeon.removeRelic(SneckoEye.ID);
        PGR.core.dungeon.removeRelic(TinyHouse.ID);
        PGR.core.dungeon.removeRelic(RunicPyramid.ID);
        PGR.core.dungeon.removeRelic(CeramicFish.ID);
        PGR.core.dungeon.removeRelic(IncenseBurner.ID);
        PGR.core.dungeon.removeRelic(PrismaticShard.ID);
        PGR.core.dungeon.addRelic(MarkOfPain.ID, AbstractRelic.RelicTier.BOSS);
        PGR.core.dungeon.addRelic(Melange.ID, AbstractRelic.RelicTier.SHOP);
        PGR.core.dungeon.addRelic(TwistedFunnel.ID, AbstractRelic.RelicTier.SHOP);
        PGR.core.dungeon.addRelic(Brimstone.ID, AbstractRelic.RelicTier.SHOP);
        PGR.core.dungeon.addRelic(CloakClasp.ID, AbstractRelic.RelicTier.RARE);
        PGR.core.dungeon.addRelic(CharonsAshes.ID, AbstractRelic.RelicTier.RARE);
        PGR.core.dungeon.addRelic(ChampionsBelt.ID, AbstractRelic.RelicTier.RARE);
        PGR.core.dungeon.addRelic(PaperCrane.ID, AbstractRelic.RelicTier.UNCOMMON);
        PGR.core.dungeon.addRelic(PaperFrog.ID, AbstractRelic.RelicTier.UNCOMMON);
        PGR.core.dungeon.addRelic(SneckoSkull.ID, AbstractRelic.RelicTier.COMMON);
        PGR.core.dungeon.addRelic(RedSkull.ID, AbstractRelic.RelicTier.COMMON);
    }

    @Override
    public PCLLoadout getCoreLoadout()
    {
        return core;
    }

    @Override
    public List<String> getStartingRelics()
    {
        return EUIUtils.list(PeriodicTable.ID);
    }
}

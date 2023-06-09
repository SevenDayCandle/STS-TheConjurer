package pinacolada.resources.conjurer;

import com.megacrit.cardcrawl.relics.*;
import pinacolada.relics.conjurer.PeriodicTable;
import pinacolada.resources.*;
import pinacolada.resources.conjurer.loadout.*;
import pinacolada.resources.loadout.PCLLoadout;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ConjurerPlayerData extends PCLAbstractPlayerData {
    private static final String MOD_ID = "Conjurer";
    private static final String BANNED_CARDS_CONJURER = PCLMainConfig.createFullID("BannedCardsConjurer");
    private static final String BANNED_RELICS_CONJURER = PCLMainConfig.createFullID("BannedRelicsConjurer");
    private static final String CARDS_COUNT_CONJURER = PCLMainConfig.createFullID("CardsCountConjurer");
    private static final String CONJURER_METER_POSITION = PCLMainConfig.createFullID("ConjurerMeterPosition");
    private static final String TROPHIES_CONJURER = PCLMainConfig.createFullID("TrophiesConjurer");

    public static ConjurerLoadout core = new ConjurerLoadout();

    public static ConjurerLoadout atelier = new Atelier();
    public static ConjurerLoadout baldursGate = new BaldursGate();
    public static ConjurerLoadout eldenRing = new EldenRing();
    public static ConjurerLoadout finalFantasy = new FinalFantasy();
    public static ConjurerLoadout genshinImpact = new GenshinImpact();
    public static ConjurerLoadout honkai = new Honkai();
    public static ConjurerLoadout monsterHunter = new MonsterHunter();
    public static ConjurerLoadout shinMegamiTensei = new ShinMegamiTensei();
    public static ConjurerLoadout slayTheSpire = new SlayTheSpire();
    public static ConjurerLoadout tales = new Tales();
    public static ConjurerLoadout theWitcher = new TheWitcher();
    public static ConjurerLoadout touhouProject = new TouhouProject();
    public static ConjurerLoadout yuGiOh = new YuGiOh();

    public ConjurerPlayerData(PCLResources<?, ?, ?, ?> resources) {
        super(resources);
    }

    @Override
    public PCLCharacterConfig getConfig() {
        return new PCLCharacterConfig(MOD_ID, BANNED_CARDS_CONJURER, BANNED_RELICS_CONJURER, CARDS_COUNT_CONJURER, CONJURER_METER_POSITION, TROPHIES_CONJURER);
    }

    @Override
    public PCLLoadout getCoreLoadout() {
        return core;
    }

    @Override
    public List<String> getStartingRelics() {
        return Collections.singletonList(PeriodicTable.DATA.ID);
    }

    @Override
    public List<PCLLoadout> getAvailableLoadouts() {
        return Arrays.asList(
                eldenRing,
                genshinImpact,
                shinMegamiTensei,
                touhouProject,
                monsterHunter
        );
    }

    public void updateRelicsForDungeon() {
        PGR.dungeon.addRelic(MarkOfPain.ID, AbstractRelic.RelicTier.BOSS);
        PGR.dungeon.addRelic(Melange.ID, AbstractRelic.RelicTier.SHOP);
        PGR.dungeon.addRelic(TwistedFunnel.ID, AbstractRelic.RelicTier.SHOP);
        PGR.dungeon.addRelic(Brimstone.ID, AbstractRelic.RelicTier.SHOP);
        PGR.dungeon.addRelic(CloakClasp.ID, AbstractRelic.RelicTier.RARE);
        PGR.dungeon.addRelic(CharonsAshes.ID, AbstractRelic.RelicTier.RARE);
        PGR.dungeon.addRelic(ChampionsBelt.ID, AbstractRelic.RelicTier.RARE);
        PGR.dungeon.addRelic(PaperCrane.ID, AbstractRelic.RelicTier.UNCOMMON);
        PGR.dungeon.addRelic(PaperFrog.ID, AbstractRelic.RelicTier.UNCOMMON);
        PGR.dungeon.addRelic(SneckoSkull.ID, AbstractRelic.RelicTier.COMMON);
        PGR.dungeon.addRelic(RedSkull.ID, AbstractRelic.RelicTier.COMMON);
    }
}

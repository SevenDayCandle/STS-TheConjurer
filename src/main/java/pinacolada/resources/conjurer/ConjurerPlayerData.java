package pinacolada.resources.conjurer;

import com.megacrit.cardcrawl.relics.*;
import pinacolada.relics.conjurer.PeriodicTable;
import pinacolada.relics.pcl.UsefulBox;
import pinacolada.resources.PCLAbstractPlayerData;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.loadout.*;
import pinacolada.resources.loadout.PCLLoadout;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ConjurerPlayerData extends PCLAbstractPlayerData<ConjurerResources, ConjurerConfig> {
    public static ConjurerLoadout core = new ConjurerLoadout();
    public static ConjurerLoadout atelier = PCLLoadout.register(new Atelier());
    public static ConjurerLoadout baldursGate = PCLLoadout.register(new BaldursGate());
    public static ConjurerLoadout eldenRing = PCLLoadout.register(new EldenRing());
    public static ConjurerLoadout finalFantasy = PCLLoadout.register(new FinalFantasy());
    public static ConjurerLoadout genshinImpact = PCLLoadout.register(new GenshinImpact());
    public static ConjurerLoadout honkai = PCLLoadout.register(new Honkai());
    public static ConjurerLoadout monsterHunter = PCLLoadout.register(new MonsterHunter());
    public static ConjurerLoadout ragnarok = PCLLoadout.register(new RagnarokOnline());
    public static ConjurerLoadout shinMegamiTensei = PCLLoadout.register(new ShinMegamiTensei());
    public static ConjurerLoadout slayTheSpire = PCLLoadout.register(new SlayTheSpire());
    public static ConjurerLoadout tales = PCLLoadout.register(new Tales());
    public static ConjurerLoadout theWitcher = PCLLoadout.register(new TheWitcher());
    public static ConjurerLoadout touhouProject = PCLLoadout.register(new TouhouProject());
    public static ConjurerLoadout yuGiOh = PCLLoadout.register(new YuGiOh());

    public ConjurerPlayerData(ConjurerResources resources) {
        super(resources);
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
        PGR.dungeon.addRelic(UsefulBox.DATA.ID, AbstractRelic.RelicTier.UNCOMMON);
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

    @Override
    public ConjurerConfig getConfig() {
        return new ConjurerConfig();
    }

    @Override
    public PCLLoadout getCoreLoadout() {
        return core;
    }

    @Override
    public List<String> getStartingRelics() {
        return Collections.singletonList(PeriodicTable.DATA.ID);
    }
}

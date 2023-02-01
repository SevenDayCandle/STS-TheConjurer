package pinacolada.resources.conjurer;

import com.megacrit.cardcrawl.relics.*;
import extendedui.EUIUtils;
import pinacolada.resources.PCLAbstractPlayerData;
import pinacolada.resources.PCLResources;
import pinacolada.resources.PGR;
import pinacolada.resources.loadout.PCLLoadout;

import java.util.List;

public class ConjurerPlayerData extends PCLAbstractPlayerData
{
    public static ConjurerLoadout core = new ConjurerLoadout(-1, 0);
    public static ConjurerLoadout genshinImpact = new ConjurerLoadout(0, 0);
    public static ConjurerLoadout shinMegamiTensei = new ConjurerLoadout(1, 0);
    public static ConjurerLoadout touhouProject = new ConjurerLoadout(2, 0);

    public ConjurerPlayerData(PCLResources<?,?,?> resources)
    {
        super(resources);
    }

    @Override
    public List<PCLLoadout> getAvailableLoadouts()
    {
        return EUIUtils.list(
                genshinImpact,
                shinMegamiTensei
        );
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
}

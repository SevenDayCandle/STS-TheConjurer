package pinacolada.resources.conjurer;

import extendedui.EUIUtils;
import pinacolada.resources.PCLAbstractPlayerData;
import pinacolada.resources.PCLResources;
import pinacolada.resources.pcl.PCLLoadout;

import java.util.Collection;

public class ConjurerPlayerData extends PCLAbstractPlayerData
{
    public static ConjurerLoadout core = new ConjurerLoadout(-1, 0);
    public static ConjurerLoadout genshinImpact = new ConjurerLoadout(0, 0);
    public static ConjurerLoadout shinMegamiTensei = new ConjurerLoadout(1, 0);


    public ConjurerPlayerData(PCLResources resources)
    {
        super(resources);
    }

    @Override
    public Collection<PCLLoadout> getAvailableLoadouts()
    {
        return EUIUtils.list(
                genshinImpact,
                shinMegamiTensei
        );
    }

    @Override
    public PCLLoadout getCoreLoadout()
    {
        return core;
    }
}

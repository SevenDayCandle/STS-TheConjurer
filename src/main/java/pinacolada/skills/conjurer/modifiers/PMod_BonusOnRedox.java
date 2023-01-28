package pinacolada.skills.conjurer.modifiers;

import extendedui.EUIUtils;
import pinacolada.misc.ConjurerUseInfo;
import pinacolada.misc.PCLUseInfo;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.fields.PField_Affinity;
import pinacolada.skills.skills.base.modifiers.PMod_BonusOn;

import static pinacolada.resources.conjurer.ConjurerEnum.Cards.THE_CONJURER;

public class PMod_BonusOnRedox extends PMod_BonusOn<PField_Affinity>
{

    public static final PSkillData<PField_Affinity> DATA = register(PMod_BonusOnRedox.class, PField_Affinity.class).setColors(THE_CONJURER).selfTarget();

    public PMod_BonusOnRedox(PSkillSaveData content)
    {
        super(DATA, content);
    }

    public PMod_BonusOnRedox()
    {
        this(0);
    }

    public PMod_BonusOnRedox(int amount)
    {
        super(DATA, amount);
    }

    @Override
    public String getSubText()
    {
        return ConjurerResources.conjurer.tooltips.redox.title;
    }

    @Override
    public boolean meetsCondition(PCLUseInfo info)
    {
        ConjurerUseInfo cInfo = EUIUtils.safeCast(info, ConjurerUseInfo.class);
        if (cInfo == null)
        {
            return false;
        }
        return fields.affinities.isEmpty() ? cInfo.reactions.hasRedox() : EUIUtils.all(fields.affinities, cInfo.reactions::hasRedox);
    }
}

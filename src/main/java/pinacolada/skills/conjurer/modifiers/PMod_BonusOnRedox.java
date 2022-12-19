package pinacolada.skills.conjurer.modifiers;

import extendedui.EUIUtils;
import pinacolada.cards.base.PCLUseInfo;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.skills.base.modifiers.PMod_BonusOn;

import static pinacolada.resources.conjurer.ConjurerEnum.Cards.THE_CONJURER;
import static pinacolada.skills.PSkill.PCLEffectType.General;

public class PMod_BonusOnRedox extends PMod_BonusOn
{

    public static final PSkillData DATA = register(PMod_BonusOnRedox.class, General).setColors(THE_CONJURER).selfTarget();

    public PMod_BonusOnRedox(PSkillSaveData content)
    {
        super(content);
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
    public String getConditionSampleText()
    {
        return ConjurerResources.conjurer.tooltips.redox.title;
    }

    @Override
    public boolean meetsCondition(PCLUseInfo info)
    {
        return affinities.isEmpty() ? info.reactions.hasRedox() : EUIUtils.all(affinities, info.reactions::hasRedox);
    }
}

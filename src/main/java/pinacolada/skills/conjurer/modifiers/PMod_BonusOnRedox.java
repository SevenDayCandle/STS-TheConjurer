package pinacolada.skills.conjurer.modifiers;

import pinacolada.cards.base.PCLUseInfo;
import pinacolada.resources.PGR;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.skills.base.modifiers.PMod_BonusOn;

import static pinacolada.resources.PCLEnum.Cards.THE_ETERNAL;
import static pinacolada.skills.PSkill.PCLEffectType.General;

public class PMod_BonusOnRedox extends PMod_BonusOn
{

    public static final PSkillData DATA = register(PMod_BonusOnRedox.class, General).setColors(THE_ETERNAL).selfTarget();

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
        return PGR.core.tooltips.redox.title;
    }

    @Override
    public boolean meetsCondition(PCLUseInfo info)
    {
        return info.reactions.hasRedox();
    }
}

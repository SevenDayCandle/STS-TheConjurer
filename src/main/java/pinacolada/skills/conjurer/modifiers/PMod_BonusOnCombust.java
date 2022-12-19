package pinacolada.skills.conjurer.modifiers;

import extendedui.EUIUtils;
import pinacolada.cards.base.PCLUseInfo;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.skills.base.modifiers.PMod_BonusOn;

import static pinacolada.resources.conjurer.ConjurerEnum.Cards.THE_CONJURER;
import static pinacolada.skills.PSkill.PCLEffectType.General;

public class PMod_BonusOnCombust extends PMod_BonusOn
{

    public static final PSkillData DATA = register(PMod_BonusOnCombust.class, General).setColors(THE_CONJURER).selfTarget();

    public PMod_BonusOnCombust(PSkillSaveData content)
    {
        super(content);
    }

    public PMod_BonusOnCombust()
    {
        this(0);
    }

    public PMod_BonusOnCombust(int amount)
    {
        super(DATA, amount);
    }

    @Override
    public String getConditionSampleText()
    {
        return ConjurerResources.conjurer.tooltips.combust.title;
    }

    @Override
    public boolean meetsCondition(PCLUseInfo info)
    {
        return affinities.isEmpty() ? info.reactions.hasCombust() : EUIUtils.all(affinities, info.reactions::hasCombust);
    }
}

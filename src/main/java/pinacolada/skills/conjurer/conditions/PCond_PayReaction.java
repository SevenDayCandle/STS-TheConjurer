package pinacolada.skills.conjurer.conditions;

import pinacolada.cards.base.PCLCardTarget;
import pinacolada.cards.base.PCLUseInfo;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PCond;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.ui.combat.ConjurerReactionMeter;

import static pinacolada.resources.conjurer.ConjurerEnum.Cards.THE_CONJURER;

public class PCond_PayReaction extends PCond
{
    public static final PSkillData DATA = register(PCond_PayReaction.class, PField_Empty.class)
            .setColors(THE_CONJURER)
            .selfTarget();

    public PCond_PayReaction(PSkillSaveData content)
    {
        super(content);
    }

    public PCond_PayReaction()
    {
        super(DATA, PCLCardTarget.None, 1);
    }

    public PCond_PayReaction(int amount)
    {
        super(DATA, PCLCardTarget.None, amount);
    }

    @Override
    public boolean checkCondition(PCLUseInfo info, boolean isUsing, boolean fromTrigger)
    {
        if (isUsing)
        {
            return ConjurerReactionMeter.meter.trySpendCount(amount);
        }
        return ConjurerReactionMeter.meter.getReactionCount() >= amount;
    }

    @Override
    public String getSampleText()
    {
        return TEXT.actions.pay("X", ConjurerResources.conjurer.tooltips.reaction.title);
    }

    @Override
    public String getSubText()
    {
        return capital(TEXT.actions.pay(amount, ConjurerResources.conjurer.tooltips.reaction.title), true);
    }
}

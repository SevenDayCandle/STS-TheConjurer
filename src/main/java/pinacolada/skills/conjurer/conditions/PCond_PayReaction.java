package pinacolada.skills.conjurer.conditions;

import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.misc.PCLUseInfo;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.fields.PField_Empty;
import pinacolada.ui.combat.ConjurerReactionMeter;

import static pinacolada.resources.conjurer.ConjurerEnum.Cards.THE_CONJURER;

public class PCond_PayReaction extends PCond<PField_Empty>
{
    public static final PSkillData<PField_Empty> DATA = register(PCond_PayReaction.class, PField_Empty.class)
            .setColors(THE_CONJURER)
            .selfTarget();

    public PCond_PayReaction(PSkillSaveData content)
    {
        super(DATA, content);
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

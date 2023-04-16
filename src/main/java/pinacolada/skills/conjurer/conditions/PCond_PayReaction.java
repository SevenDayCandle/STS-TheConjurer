package pinacolada.skills.conjurer.conditions;

import extendedui.interfaces.delegates.ActionT0;
import pinacolada.actions.PCLAction;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.fields.PField_Not;
import pinacolada.skills.skills.PActiveCond;
import pinacolada.ui.combat.ConjurerReactionMeter;

import static pinacolada.resources.conjurer.ConjurerEnum.Cards.THE_CONJURER;

public class PCond_PayReaction extends PActiveCond<PField_Not>
{
    public static final PSkillData<PField_Not> DATA = register(PCond_PayReaction.class, PField_Not.class)
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
    public boolean checkCondition(PCLUseInfo info, boolean isUsing, PSkill<?> triggerSource)
    {
        return ConjurerReactionMeter.meter.getReactionCount() >= amount;
    }

    @Override
    public String getSampleText(PSkill<?> callingSkill)
    {
        return TEXT.act_pay("X", ConjurerResources.conjurer.tooltips.reaction.title);
    }

    @Override
    public String getSubText()
    {
        return capital(TEXT.act_pay(getAmountRawString(), ConjurerResources.conjurer.tooltips.reaction.title), true);
    }

    @Override
    protected PCLAction<?> useImpl(PCLUseInfo pclUseInfo, ActionT0 actionT0, ActionT0 actionT01)
    {
        return getActions().callback(() -> ConjurerReactionMeter.meter.trySpendCount(amount));
    }
}

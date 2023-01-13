package pinacolada.skills.conjurer.modifiers;

import extendedui.EUIRM;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.cards.base.PCLUseInfo;
import pinacolada.resources.conjurer.ConjurerEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PSkill;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.fields.PField_Not;
import pinacolada.ui.combat.ConjurerReactionMeter;

public class PMod_PerReaction extends PMod<PField_Not>
{

    public static final PSkillData<PField_Not> DATA = register(PMod_PerReaction.class, PField_Not.class, ConjurerEnum.Cards.THE_CONJURER).selfTarget();

    public PMod_PerReaction(PSkillSaveData content)
    {
        super(DATA, content);
    }

    public PMod_PerReaction()
    {
        super(DATA);
    }

    public PMod_PerReaction(int amount)
    {
        super(DATA, PCLCardTarget.None, amount);
    }

    @Override
    public String getSampleText()
    {
        return TEXT.conditions.per("X", ConjurerResources.conjurer.tooltips.reaction.title);
    }

    @Override
    public String getSubText()
    {
        return amount > 1 ? EUIRM.strings.numNoun(getAmountRawString(), ConjurerResources.conjurer.tooltips.reaction.title) : ConjurerResources.conjurer.tooltips.reaction.title;
    }

    @Override
    public String getText(boolean addPeriod)
    {
        String payString = fields.not ? (capital(TEXT.actions.pay("X", ConjurerResources.conjurer.tooltips.reaction.title), true) + ": ") : "";
        return payString + super.getText(addPeriod);
    }

    @Override
    public void use(PCLUseInfo info)
    {
        if (this.childEffect != null)
        {
            updateChildAmount(info);
            if (fields.not)
            {
                ConjurerReactionMeter.meter.trySpendCount(ConjurerReactionMeter.meter.getReactionCount());
            }
            this.childEffect.use(info);
        }
    }

    @Override
    public int getModifiedAmount(PSkill be, PCLUseInfo info)
    {
        return be.baseAmount * ConjurerReactionMeter.meter.getReactionCount() / Math.max(1, amount);
    }
}

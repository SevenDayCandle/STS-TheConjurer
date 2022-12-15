package pinacolada.skills.conjurer.modifiers;

import extendedui.EUIRM;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.cards.base.PCLUseInfo;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.PGR;
import pinacolada.skills.PMod;
import pinacolada.skills.PSkill;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.ui.combat.ConjurerReactionMeter;

import static pinacolada.skills.PSkill.PCLEffectType.General;

public class PMod_PerReaction extends PMod
{

    public static final PSkillData DATA = register(PMod_PerReaction.class, General, PCLEnum.Cards.THE_CONJURER).selfTarget();

    public PMod_PerReaction(PSkillSaveData content)
    {
        super(content);
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
        return TEXT.conditions.per("X", PGR.core.tooltips.reaction.title);
    }

    @Override
    public String getSubText()
    {
        return amount > 1 ? EUIRM.strings.numNoun(getAmountRawString(), PGR.core.tooltips.reaction.title) : PGR.core.tooltips.reaction.title;
    }

    @Override
    public String getText(boolean addPeriod)
    {
        String payString = alt ? (capital(TEXT.actions.pay("X", PGR.core.tooltips.reaction.title), true) + ": ") : "";
        return payString + super.getText(addPeriod);
    }

    @Override
    public void use(PCLUseInfo info)
    {
        if (this.childEffect != null)
        {
            updateChildAmount(info);
            if (alt)
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

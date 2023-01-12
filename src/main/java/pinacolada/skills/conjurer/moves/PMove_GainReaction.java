package pinacolada.skills.conjurer.moves;

import pinacolada.cards.base.PCLUseInfo;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.skills.base.moves.PMove_Gain;
import pinacolada.ui.combat.ConjurerReactionMeter;

public class PMove_GainReaction extends PMove_Gain
{
    public static final PSkillData DATA = register(PMove_GainReaction.class, PField_Empty.class, ConjurerEnum.Cards.THE_CONJURER);

    public PMove_GainReaction()
    {
        this(1);
    }

    public PMove_GainReaction(PSkillSaveData content)
    {
        super(content);
    }

    public PMove_GainReaction(int amount)
    {
        super(DATA, amount);
    }

    @Override
    public String gainText()
    {
        return ConjurerResources.conjurer.tooltips.reaction.title;
    }

    @Override
    public void use(PCLUseInfo info)
    {
        ConjurerReactionMeter.meter.addCount(amount, true);
        super.use(info);
    }
}

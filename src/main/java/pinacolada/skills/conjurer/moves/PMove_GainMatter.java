package pinacolada.skills.conjurer.moves;

import pinacolada.actions.PCLActions;
import pinacolada.actions.powers.GainReaction;
import pinacolada.annotations.VisibleSkill;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.resources.conjurer.ConjurerEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.fields.PField_Empty;
import pinacolada.skills.skills.base.moves.PMove_Gain;

@VisibleSkill
public class PMove_GainMatter extends PMove_Gain {
    public static final PSkillData<PField_Empty> DATA = register(PMove_GainMatter.class, PField_Empty.class, ConjurerEnum.Cards.THE_CONJURER)
            .noTarget();

    public PMove_GainMatter() {
        this(1);
    }

    public PMove_GainMatter(int amount) {
        super(DATA, PCLCardTarget.None, amount);
    }

    public PMove_GainMatter(PSkillSaveData content) {
        super(DATA, content);
    }

    @Override
    public String gainText() {
        return ConjurerResources.conjurer.tooltips.matter.title;
    }

    @Override
    public void use(PCLUseInfo info, PCLActions order) {
        order.add(new GainReaction(amount));
        super.use(info, order);
    }
}

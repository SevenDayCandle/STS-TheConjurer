package pinacolada.skills.conjurer.conditions;

import extendedui.interfaces.delegates.ActionT1;
import pinacolada.actions.PCLAction;
import pinacolada.actions.PCLActions;
import pinacolada.actions.powers.GainReaction;
import pinacolada.annotations.VisibleSkill;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.fields.PField_Not;
import pinacolada.skills.skills.PActiveCond;

import static pinacolada.resources.conjurer.ConjurerEnum.Cards.THE_CONJURER;

@VisibleSkill
public class PCond_PayMatter extends PActiveCond<PField_Not> {
    public static final PSkillData<PField_Not> DATA = register(PCond_PayMatter.class, PField_Not.class)
            .setColors(THE_CONJURER)
            .noTarget();

    public PCond_PayMatter(PSkillSaveData content) {
        super(DATA, content);
    }

    public PCond_PayMatter() {
        super(DATA, PCLCardTarget.None, 1);
    }

    public PCond_PayMatter(int amount) {
        super(DATA, PCLCardTarget.None, amount);
    }

    @Override
    public boolean checkCondition(PCLUseInfo info, boolean isUsing, PSkill<?> triggerSource) {
        return ConjurerReactionMeter.meter.getMatter() >= amount;
    }

    @Override
    public String getSampleText(PSkill<?> callingSkill, PSkill<?> parentSkill) {
        return TEXT.act_pay("X", ConjurerResources.conjurer.tooltips.matter.title);
    }

    @Override
    public String getSubText(PCLCardTarget perpsective, Object requestor) {
        return capital(TEXT.act_pay(getAmountRawString(), ConjurerResources.conjurer.tooltips.matter.title), true);
    }

    @Override
    protected PCLAction<?> useImpl(PCLUseInfo info, PCLActions order, ActionT1<PCLUseInfo> onComplete, ActionT1<PCLUseInfo> onFail) {
        return order.add(new GainReaction(-amount)).addCallback(() -> onComplete.invoke(info));
    }
}

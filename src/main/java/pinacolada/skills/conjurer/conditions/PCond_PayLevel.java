package pinacolada.skills.conjurer.conditions;

import extendedui.EUIRM;
import extendedui.EUIUtils;
import extendedui.interfaces.delegates.ActionT1;
import pinacolada.actions.PCLAction;
import pinacolada.actions.PCLActions;
import pinacolada.actions.affinity.AddAffinityLevel;
import pinacolada.actions.utility.SequentialAction;
import pinacolada.annotations.VisibleSkill;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerEnum;
import pinacolada.skills.PSkill;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.fields.PField_Affinity;
import pinacolada.skills.skills.PActiveCond;

@VisibleSkill
public class PCond_PayLevel extends PActiveCond<PField_Affinity> {
    public static final PSkillData<PField_Affinity> DATA = register(PCond_PayLevel.class, PField_Affinity.class, ConjurerEnum.Cards.THE_CONJURER)
            .noTarget();

    public PCond_PayLevel(PSkillSaveData content) {
        super(DATA, content);
    }

    public PCond_PayLevel() {
        super(DATA, PCLCardTarget.None, 1);
    }

    public PCond_PayLevel(int amount, PCLAffinity... affinities) {
        super(DATA, PCLCardTarget.None, amount);
        fields.setAffinity(affinities);
    }

    @Override
    public boolean checkCondition(PCLUseInfo info, boolean isUsing, PSkill<?> triggerSource) {
        if (fields.affinities.isEmpty()) {
            return fields.not ^ ConjurerReactionMeter.meter.getLevel(PCLAffinity.General) >= refreshAmount(info);
        }
        else {
            int am = refreshAmount(info);
            for (PCLAffinity affinity : fields.affinities) {
                if (ConjurerReactionMeter.meter.getLevel(affinity) < am) {
                    return fields.not;
                }
            }
        }
        return !fields.not;
    }

    @Override
    public String getSampleText(PSkill<?> callingSkill, PSkill<?> parentSkill) {
        return TEXT.act_pay(TEXT.subjects_x, PGR.core.tooltips.level.title);
    }

    @Override
    public String getSubText(PCLCardTarget perspective, Object requestor) {
        return capital(TEXT.act_pay(getAmountRawString(), EUIRM.strings.adjNoun(fields.getAffinityAndString(), PGR.core.tooltips.level.title)), true);
    }

    @Override
    protected PCLAction<?> useImpl(PCLUseInfo info, PCLActions order, ActionT1<PCLUseInfo> onComplete, ActionT1<PCLUseInfo> onFail) {
        return order.callback(new SequentialAction(EUIUtils.map(fields.affinities, af -> new AddAffinityLevel(af, -refreshAmount(info)))), () -> {
            if (conditionMetCache) {
                onComplete.invoke(info);
            }
            else {
                onFail.invoke(info);
            }
        });
    }
}

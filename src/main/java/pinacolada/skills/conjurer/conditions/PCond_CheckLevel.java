package pinacolada.skills.conjurer.conditions;

import extendedui.EUIRM;
import pinacolada.annotations.VisibleSkill;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.interfaces.subscribers.OnIntensifySubscriber;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerEnum;
import pinacolada.skills.PSkill;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.fields.PField_Affinity;
import pinacolada.skills.skills.PPassiveCond;

import java.util.ArrayList;

@VisibleSkill
public class PCond_CheckLevel extends PPassiveCond<PField_Affinity> {
    public static final PSkillData<PField_Affinity> DATA = register(PCond_CheckLevel.class, PField_Affinity.class, ConjurerEnum.Cards.THE_CONJURER)
            .noTarget();

    public PCond_CheckLevel() {
        this(1);
    }

    public PCond_CheckLevel(PSkillSaveData content) {
        super(DATA, content);
    }

    public PCond_CheckLevel(int amount, PCLAffinity... stance) {
        super(DATA, PCLCardTarget.None, amount);
        fields.setAffinity(stance);
    }

    @Override
    public boolean checkCondition(PCLUseInfo info, boolean isUsing, PSkill<?> triggerSource) {
        if (fields.affinities.isEmpty()) {
            return fields.not ^ ConjurerReactionMeter.meter.getLevel(PCLAffinity.General) >= amount;
        }
        else {
            for (PCLAffinity affinity : fields.affinities) {
                if (ConjurerReactionMeter.meter.getLevel(affinity) < amount) {
                    return fields.not;
                }
            }
        }
        return !fields.not;
    }

    public int getQualifierRange() {
        return fields.getQualiferRange();
    }

    public String getQualifierText(int i) {
        return fields.getQualifierText(i);
    }

    public ArrayList<Integer> getQualifiers(PCLUseInfo info, boolean conditionPassed) {
        return fields.getQualifiers(info);
    }

    @Override
    public String getSampleText(PSkill<?> callingSkill, PSkill<?> parentSkill) {
        return TEXT.cond_levelItem(TEXT.subjects_x, PGR.core.tooltips.affinityGeneral.title);
    }

    @Override
    public String getSubText(PCLCardTarget perspective, Object requestor) {
        if (isBranch()) {
            return EUIRM.strings.nounVerb(TEXT.subjects_you, PGR.core.tooltips.level.title);
        }
        return TEXT.cond_ifX(TEXT.cond_levelItem(getAmountRawString(), fields.getAffinityChoiceString()));
    }
}

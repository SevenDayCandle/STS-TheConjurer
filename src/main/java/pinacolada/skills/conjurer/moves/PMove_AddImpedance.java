package pinacolada.skills.conjurer.moves;

import com.megacrit.cardcrawl.core.AbstractCreature;
import extendedui.EUIRM;
import extendedui.EUIUtils;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleSkill;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.powers.conjurer.AffinityPropertyPower;
import pinacolada.resources.conjurer.ConjurerEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.fields.PField_Affinity;

import java.util.List;

@VisibleSkill
public class PMove_AddImpedance extends PMove<PField_Affinity> {
    public static final PSkillData<PField_Affinity> DATA = register(PMove_AddImpedance.class, PField_Affinity.class, ConjurerEnum.Cards.THE_CONJURER);

    public PMove_AddImpedance(PSkillSaveData content) {
        super(DATA, content);
    }

    public PMove_AddImpedance(int amount, PCLAffinity... stance) {
        this(PCLCardTarget.Single, amount, stance);
    }

    public PMove_AddImpedance(PCLCardTarget target, int amount, PCLAffinity... stance) {
        super(DATA, PCLCardTarget.Self, amount);
        fields.setAffinity(stance);
    }

    protected void apply(PCLUseInfo info, PCLAffinity affinity, PCLActions order) {
        if (affinity != null) {
            List<? extends AbstractCreature> targets = getTargetListAsNew(info);
            for (AbstractCreature t : targets) {
                order.applyPower(new AffinityPropertyPower(affinity, info.source, t, refreshAmount(info)));
            }
        }
    }

    @Override
    public String getSampleText(PSkill<?> callingSkill, PSkill<?> parentSkill) {
        return TEXT.act_gainAmount(TEXT.subjects_x, ConjurerResources.conjurer.tooltips.reaction);
    }

    @Override
    public String getSubText(PCLCardTarget perspective, Object requestor) {
        String amountString = getAmountRawString();
        String joinedString = fields.affinities.isEmpty() ? TEXT.subjects_randomX(ConjurerResources.conjurer.tooltips.impedance.title) : EUIRM.strings.adjNoun(fields.getAffinityAndString(), ConjurerResources.conjurer.tooltips.impedance.title);
        switch (target) {
            case Self:
                if (isFromCreature() || perspective != PCLCardTarget.Self) {
                    return amount < 0 ? TEXT.act_removeFrom(EUIRM.strings.numNoun(amountString, joinedString), getTargetStringPerspective(perspective)) : TEXT.act_giveTargetAmount(getTargetStringPerspective(perspective), amountString, joinedString);
                }
            case None:
                return amount < 0 ? TEXT.act_loseAmount(amountString, joinedString)
                        : TEXT.act_gainAmount(amountString, joinedString);
            default:
                return amount < 0 ? TEXT.act_removeFrom(EUIRM.strings.numNoun(amountString, joinedString), getTargetStringPerspective(perspective))
                        : TEXT.act_giveTargetAmount(getTargetStringPerspective(perspective), amountString, joinedString);
        }
    }

    @Override
    public void use(PCLUseInfo info, PCLActions order) {
        int actualAmount = refreshAmount(info);
        if (fields.affinities.isEmpty()) {
            order.tryChooseAffinitySkill(getName(), actualAmount, info.source, info.target, EUIUtils.map(PCLAffinity.getAvailableAffinities(), a -> new PMove_AddImpedance(target, actualAmount, a)));
        }
        else if (fields.affinities.size() == 1) {
            apply(info, fields.affinities.get(0), order);
        }
        else {
            order.tryChooseAffinitySkill(getName(), actualAmount, info.source, info.target, EUIUtils.map(fields.affinities, a -> new PMove_AddImpedance(target, actualAmount, a)));
        }
        super.use(info, order);
    }
}

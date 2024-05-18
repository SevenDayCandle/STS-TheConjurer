package pinacolada.skills.conjurer.moves;

import extendedui.EUIUtils;
import pinacolada.actions.PCLActions;
import pinacolada.actions.affinity.AddAffinityLevel;
import pinacolada.annotations.VisibleSkill;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.resources.conjurer.ConjurerEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.fields.PField_Affinity;

@VisibleSkill
public class PMove_AddLevel extends PMove<PField_Affinity> {
    public static final PSkillData<PField_Affinity> DATA = register(PMove_AddLevel.class, PField_Affinity.class, ConjurerEnum.Cards.THE_CONJURER)
            .noTarget();

    public PMove_AddLevel(PSkillSaveData content) {
        super(DATA, content);
    }

    public PMove_AddLevel(int amount, PCLAffinity... stance) {
        super(DATA, PCLCardTarget.Self, amount);
        fields.setAffinity(stance);
    }

    @Override
    public String getSampleText(PSkill<?> callingSkill, PSkill<?> parentSkill) {
        return TEXT.act_gainAmount(TEXT.subjects_x, ConjurerResources.conjurer.tooltips.reaction);
    }

    @Override
    public String getSubText(PCLCardTarget perspective, Object requestor) {
        String base = TEXT.act_giveTargetAmount(fields.getAffinityChoiceString(), (amount > 0 ? ("+ " + getAmountRawString(requestor)) : getAmountRawString(requestor)), plural(ConjurerResources.conjurer.tooltips.reaction, requestor));
        return fields.random ? TEXT.subjects_randomly(base) : base;
    }

    @Override
    public void use(PCLUseInfo info, PCLActions order) {
        int actualAmount = refreshAmount(info);
        if (fields.affinities.isEmpty()) {
            order.tryChooseAffinitySkill(getName(), actualAmount, info.source, info.target, EUIUtils.map(PCLAffinity.getAvailableAffinities(), a -> new PMove_AddLevel(actualAmount, a)));
        }
        else if (fields.affinities.size() == 1) {
            order.add(new AddAffinityLevel(fields.affinities.get(0), actualAmount));
        }
        else {
            order.tryChooseAffinitySkill(getName(), actualAmount, info.source, info.target, EUIUtils.map(fields.affinities, a -> new PMove_AddLevel(actualAmount, a)));
        }
        super.use(info, order);
    }
}

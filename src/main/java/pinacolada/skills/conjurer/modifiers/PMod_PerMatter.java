package pinacolada.skills.conjurer.modifiers;

import pinacolada.annotations.VisibleSkill;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.resources.conjurer.ConjurerEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.fields.PField_Not;
import pinacolada.skills.skills.base.modifiers.PMod_Per;

@VisibleSkill
public class PMod_PerMatter extends PMod_Per<PField_Not> {

    public static final PSkillData<PField_Not> DATA = register(PMod_PerMatter.class, PField_Not.class, ConjurerEnum.Cards.THE_CONJURER).noTarget();

    public PMod_PerMatter(PSkillSaveData content) {
        super(DATA, content);
    }

    public PMod_PerMatter() {
        super(DATA);
    }

    public PMod_PerMatter(int amount) {
        super(DATA, PCLCardTarget.None, amount);
    }

    @Override
    public int getMultiplier(PCLUseInfo info, boolean isUsing) {
        return ConjurerReactionMeter.meter.getMatter();
    }

    @Override
    public String getSubText(PCLCardTarget perspective, Object requestor) {
        return ConjurerResources.conjurer.tooltips.matter.title;
    }
}

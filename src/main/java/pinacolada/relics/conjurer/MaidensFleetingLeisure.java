package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.PTrigger;
import pinacolada.skills.skills.special.moves.PMove_RestoreCardHP;

@VisibleRelic
public class MaidensFleetingLeisure extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(MaidensFleetingLeisure.class, ConjurerResources.conjurer)
            .setProps(RelicTier.UNCOMMON, LandingSound.MAGICAL);

    public MaidensFleetingLeisure() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(PCond.onWithdraw(), PMultiSkill.join(new PMove_RestoreCardHP(3).useParent(true), PMove.modifyDamage(1).useParent(true))));
    }
}
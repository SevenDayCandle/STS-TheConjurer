package pinacolada.relics.conjurer;

import extendedui.utilities.CostFilter;
import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class JumpyDumptyPartyPopper extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(JumpyDumptyPartyPopper.class, ConjurerResources.conjurer)
            .setProps(RelicTier.SPECIAL, LandingSound.MAGICAL)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public JumpyDumptyPartyPopper() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.interactablePerCombat(1,
                PMod.exhaustPer(0, PCLCardGroupHelper.DiscardPile).edit(f -> f.setCost(CostFilter.Cost0)),
                PMultiSkill.join(PMove.playCopy(1, PCLCardTarget.RandomEnemy).useParent(true), PMove.takeDamage(2, PCLAttackVFX.SMALL_EXPLOSION.key))));
    }
}
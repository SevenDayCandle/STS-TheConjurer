package pinacolada.cards.conjurer.series.eldenring;


import com.megacrit.cardcrawl.core.AbstractCreature;
import extendedui.EUIUtils;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.skills.skills.PTrigger;
import pinacolada.ui.combat.ConjurerReactionMeter;

@VisibleCard
public class ScarletAeonia extends PCLCard {
    public static final PCLCardData DATA = register(ScarletAeonia.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public ScarletAeonia() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.applyToEveryone(7, PCLElementHelper.Aer));
        addApplyPower(PCLCardTarget.Single, -1, PTrigger.when(PCond.onTurnEnd(), getSpecialMove(0, this::specialMove, 1, 3)));
    }

    public void specialMove(PSpecialSkill move, PCLUseInfo info) {
        AbstractCreature owner = move.getOwnerCreature();
        if (owner != null) {
            int poisonAmount = EUIUtils.sumInt(EUIUtils.filter(owner.powers, po -> ConjurerReactionMeter.meter.isPowerElemental(po.ID, PCLAffinity.Green)), po -> po.amount);
            if (poisonAmount > 0) {
                PCLActions.bottom.applyPower(owner, PCLCardTarget.Single, PCLPowerHelper.Poison, poisonAmount);
            }
        }
    }
}

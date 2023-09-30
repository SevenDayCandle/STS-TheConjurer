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
import pinacolada.cards.conjurer.special.Biohazard;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.conjurer.VentusPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.skills.skills.PTrigger;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class ScarletAeonia extends PCLCard {
    public static final PCLCardData DATA = register(ScarletAeonia.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE)
            .setCostUpgrades(-1)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public ScarletAeonia() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.obtainCard(2, Biohazard.DATA.ID));
        addApplyPower(PCLCardTarget.Single, -1, PTrigger.when(PCond.onTurnEnd(), getSpecialMove(0, this::specialMove, 1, 3))).setVFX(ConjurerEFK.EVFXForge02_08_BloomforgeWard);
    }

    public void specialMove(PSpecialSkill move, PCLUseInfo info, PCLActions order) {
        AbstractCreature owner = move.getOwnerCreature();
        if (!GameUtilities.isDeadOrEscaped(owner)) {
            int poisonAmount = EUIUtils.sumInt(EUIUtils.filter(owner.powers, po -> ConjurerReactionMeter.meter.isPowerElemental(po.ID, PCLAffinity.Green)), po -> po.amount);
            if (poisonAmount > 0) {
                order.applyPower(owner, PCLPowerData.Poison, poisonAmount);
                order.removePower(owner, owner, VentusPower.POWER_ID);
            }
        }
    }
}

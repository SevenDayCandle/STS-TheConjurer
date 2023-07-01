package pinacolada.relics.conjurer;

import extendedui.EUIGameUtils;
import extendedui.EUIUtils;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.GameUtilities;

@VisibleRelic
public class PeriodicTable extends PCLRelic {
    public static final PCLRelicData DATA = register(PeriodicTable.class, ConjurerResources.conjurer)
            .setProps(RelicTier.STARTER, LandingSound.SOLID);
    protected int current;

    public PeriodicTable() {
        super(DATA);
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();
        current = 0;
        updateDescription(GameUtilities.getPlayerClass());
    }

    @Override
    public void atTurnStartPostDraw() {
        super.atTurnStartPostDraw();
        PCLElementHelper element = PCLElementHelper.get(PCLAffinity.basic()[current]);
        PCLActions.bottom.applyPower(PCLCardTarget.RandomEnemy, element, getValue());
        current = (current + 1) % 4;
        updateDescription(GameUtilities.getPlayerClass());
    }

    @Override
    public int getValue() {
        return 2;
    }

    @Override
    public String getUpdatedDescription() {
        if (EUIGameUtils.inBattle()) {
            PCLElementHelper element = PCLElementHelper.get(PCLAffinity.basic()[current]);
            return super.getUpdatedDescription() + EUIUtils.DOUBLE_SPLIT_LINE + formatDescription(1, element.getTooltip().getTitleOrIcon());
        }
        return super.getUpdatedDescription();
    }
}
package pinacolada.powers.conjurer;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisiblePower;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.dungeon.AffinityReactions;
import pinacolada.effects.PCLSFX;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;

@VisiblePower
public class VentusPower extends AbstractPCLElementalPower {
    public static final ElementPowerData DATA = registerElement(VentusPower.class, PCLAffinity.Green)
            .setTooltip(ConjurerResources.conjurer.tooltips.ventus);

    public VentusPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(DATA, owner, source, amount);
    }

    @Override
    public AbstractGameAction.AttackEffect getAttackEffect() {
        return PCLEnum.AttackEffect.WIND;
    }

    @Override
    public void onReact(AbstractCreature source, AffinityReactions reactions) {
        PCLActions.bottom.applyPower(AbstractDungeon.player, FlowPower.DATA, MathUtils.ceil(calculateValue(reactions)));
        super.onReact(source, reactions);
    }

    @Override
    public void playApplyPowerSfx() {
        PCLSFX.play(PCLSFX.WIND, 0.75f, 0.85f);
    }
}

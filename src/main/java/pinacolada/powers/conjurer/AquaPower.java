package pinacolada.powers.conjurer;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisiblePower;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.dungeon.AffinityReactions;
import pinacolada.effects.PCLSFX;
import pinacolada.monsters.PCLCardAlly;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;

@VisiblePower
public class AquaPower extends AbstractPCLElementalPower {
    public static final ElementPowerData DATA = registerElement(AquaPower.class, PCLAffinity.Blue)
            .setTooltip(ConjurerResources.conjurer.tooltips.aqua);

    public AquaPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(DATA, owner, source, amount);
    }

    @Override
    public AbstractGameAction.AttackEffect getAttackEffect() {
        return PCLEnum.AttackEffect.ICE;
    }

    @Override
    public void onReact(AbstractCreature source, AffinityReactions reactions) {
        if (!(owner instanceof PCLCardAlly)) {
            PCLActions.bottom.applyPower(source, owner, CooledPower.DATA, (int) calculateValue(reactions), false);
        }
        super.onReact(source, reactions);
    }

    @Override
    public void playApplyPowerSfx() {
        PCLSFX.play(PCLSFX.ORB_FROST_CHANNEL, 0.95f, 1.05f);
    }
}

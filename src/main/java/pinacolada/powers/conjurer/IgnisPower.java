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
public class IgnisPower extends AbstractPCLElementalPower {
    public static final ElementPowerData DATA = registerElement(IgnisPower.class, PCLAffinity.Red)
            .setTooltip(ConjurerResources.conjurer.tooltips.ignis);

    public IgnisPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(DATA, owner, source, amount);
    }

    @Override
    public AbstractGameAction.AttackEffect getAttackEffect() {
        return PCLEnum.AttackEffect.BURN;
    }

    @Override
    public void onReact(AbstractCreature source, AffinityReactions reactions) {
        if (!(owner instanceof PCLCardAlly)) {
            PCLActions.bottom.applyPower(source, owner, BlastedPower.DATA, (int) calculateValue(reactions));
        }
        super.onReact(source, reactions);
    }

    @Override
    public void playApplyPowerSfx() {
        PCLSFX.play(PCLSFX.ATTACK_FIRE, 0.95f, 1.05f);
    }
}
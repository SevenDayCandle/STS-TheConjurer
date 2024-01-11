package pinacolada.powers.conjurer;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisiblePower;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.dungeon.AffinityReactions;
import pinacolada.effects.PCLSFX;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;

@VisiblePower
public class PetraPower extends AbstractPCLElementalPower {
    public static final ElementPowerData DATA = registerElement(PetraPower.class, PCLAffinity.Orange)
            .setType(PowerType.DEBUFF)
            .setTooltip(ConjurerResources.conjurer.tooltips.petra);

    public PetraPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(DATA, owner, source, amount);
    }

    @Override
    public AbstractGameAction.AttackEffect getAttackEffect() {
        return PCLEnum.AttackEffect.EARTH;
    }

    @Override
    public void playApplyPowerSfx() {
        PCLSFX.play(PCLSFX.PCL_ORB_EARTH_CHANNEL, 0.95f, 1.05f);
    }
}

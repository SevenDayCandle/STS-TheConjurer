package pinacolada.powers.conjurer;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.MinionPower;
import pinacolada.actions.PCLActions;
import pinacolada.actions.special.DieAction;
import pinacolada.annotations.VisiblePower;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.dungeon.AffinityReactions;
import pinacolada.effects.PCLSFX;
import pinacolada.monsters.PCLCardAlly;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.GameUtilities;

@VisiblePower
public class UmbraPower extends AbstractPCLElementalPower {
    public static final ElementPowerData DATA = registerElement(UmbraPower.class, PCLAffinity.Purple)
            .setType(PowerType.DEBUFF)
            .setTooltip(ConjurerResources.conjurer.tooltips.umbra);

    public UmbraPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(DATA, owner, source, amount);
    }

    @Override
    public AbstractGameAction.AttackEffect getAttackEffect() {
        return PCLEnum.AttackEffect.DARKNESS;
    }

    @Override
    public void playApplyPowerSfx() {
        PCLSFX.play(PCLSFX.PCL_DARKNESS, 0.75f, 0.85f);
    }
}

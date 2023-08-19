package pinacolada.powers.conjurer;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.MinionPower;
import pinacolada.actions.PCLActions;
import pinacolada.actions.special.DieAction;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.dungeon.AffinityReactions;
import pinacolada.effects.PCLSFX;
import pinacolada.monsters.PCLCardAlly;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.GameUtilities;

public class UmbraPower extends AbstractPCLElementalPower {
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, UmbraPower.class);
    public static final PCLAffinity AFFINITY = setAffinity(POWER_ID, PCLAffinity.Purple);
    public static final int MULTIPLIER = setMultiplier(POWER_ID, 5);

    public UmbraPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(owner, source, POWER_ID, amount);
    }

    @Override
    public float calculateValue(int amount, float multiplier) {
        return amount > 0 ? MathUtils.ceil(amount * (multiplier / (100f + 5 * owner.currentHealth))) : 0;
    }

    @Override
    public AbstractGameAction.AttackEffect getAttackEffect() {
        return PCLEnum.AttackEffect.DARKNESS;
    }

    @Override
    public void onReact(AbstractCreature source, AffinityReactions reactions) {
        if (!(owner instanceof PCLCardAlly) && (!GameUtilities.inBossRoom() || !owner.hasPower(MinionPower.POWER_ID)) && GameUtilities.chance(calculateValue(reactions))) {
            PCLActions.bottom.add(new DieAction(owner));
        }
        super.onReact(source, reactions);
    }

    @Override
    public void playApplyPowerSfx() {
        PCLSFX.play(PCLSFX.PCL_DARKNESS, 0.75f, 0.85f);
    }
}

package pinacolada.powers.conjurer;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.dungeon.AffinityReactions;
import pinacolada.effects.PCLSFX;
import pinacolada.monsters.PCLCardAlly;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;

public class AquaPower extends AbstractPCLElementalPower {
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, AquaPower.class);
    public static final PCLAffinity AFFINITY = setAffinity(POWER_ID, PCLAffinity.Blue);
    public static final int MULTIPLIER = setMultiplier(POWER_ID, 100);

    public AquaPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(owner, source, POWER_ID, amount);
    }

    @Override
    public AbstractGameAction.AttackEffect getAttackEffect() {
        return PCLEnum.AttackEffect.ICE;
    }

    @Override
    public void onReact(AbstractCreature source, AffinityReactions reactions) {
        if (!(owner instanceof PCLCardAlly)) {
            PCLActions.bottom.applyPower(source, owner, PCLElementHelper.Cooled, (int) calculateValue(reactions), false);
        }
        super.onReact(source, reactions);
    }

    @Override
    public void playApplyPowerSfx() {
        PCLSFX.play(PCLSFX.ORB_FROST_CHANNEL, 0.95f, 1.05f);
    }
}

package pinacolada.powers.conjurer;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.PCLSFX;
import pinacolada.powers.PCLPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.GameUtilities;
import pinacolada.utilities.PCLRenderHelpers;

public class CooledPower extends PCLPower {
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, CooledPower.class);
    public static final float POTENCY = 10;
    public static final Color healthBarColor = Color.SKY.cpy();
    public boolean expanded;

    public CooledPower(AbstractCreature owner, int amount) {
        super(owner, POWER_ID);
        this.priority = 0;
        initialize(amount, PowerType.DEBUFF, true);
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return super.atDamageGive(type == DamageInfo.DamageType.NORMAL ? damage - getPotency() : damage, type);
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (expanded) {
            int red = GameUtilities.getPCLCardAffinityLevel(card, PCLAffinity.Red, true);
            damage += damage * red * AbstractPCLElementalPower.getAmplifyMultiplier(PCLAffinity.Red);
            return super.atDamageReceive((!GameUtilities.isPlayer(owner) || type == DamageInfo.DamageType.NORMAL) ? damage + getPotency() : damage, type, card);
        }
        return super.atDamageReceive(type == DamageInfo.DamageType.NORMAL ? damage + getPotency() : damage, type, card);
    }

    @Override
    public void atEndOfRound() {
        super.atEndOfRound();
        removePower();
    }

    public float getPotency() {
        return this.amount / POTENCY;
    }

    @Override
    public String getUpdatedDescription() {
        return formatDescription(0, PCLRenderHelpers.decimalFormat(getPotency()));
    }

    @Override
    public float modifyOrbIncoming(float inital) {
        return super.modifyOrbIncoming(inital) + getPotency();
    }

    @Override
    public void playApplyPowerSfx() {
        PCLSFX.play(PCLSFX.ORB_FROST_DEFEND_1, 0.95f, 1.05f);
    }
}

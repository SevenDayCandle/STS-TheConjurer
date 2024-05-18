package pinacolada.powers.conjurer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.powers.PCLPower;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.GameUtilities;

public class AffinityPropertyPower extends PCLPower {
    public static final PCLPowerData DATA = register(AffinityPropertyPower.class, ConjurerResources.conjurer)
            .setLimits(-9999, 10)
            .setEndTurnBehavior(PCLPowerData.Behavior.Permanent);
    public final PCLAffinity affinity;

    public AffinityPropertyPower(PCLAffinity affinity, AbstractCreature owner, AbstractCreature source, int amount) {
        super(DATA, owner, source, amount);
        this.affinity = affinity;
        this.ID = DATA.ID + this.affinity;
        this.img = affinity.getIcon();
        updateDescription();
    }

    public static AffinityPropertyPower getPower(PCLAffinity affinity, AbstractCreature owner) {
        if (owner != null && owner.powers != null) {
            for (AbstractPower po : owner.powers) {
                if (po instanceof AffinityPropertyPower && ((AffinityPropertyPower) po).affinity == affinity) {
                    return (AffinityPropertyPower) po;
                }
            }
        }
        return null;
    }

    public static int getPowerAmount(PCLAffinity affinity, AbstractCreature owner) {
        AffinityPropertyPower po = getPower(affinity, owner);
        return po != null ? po.amount : 0;
    }

    @Override
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        return super.atDamageFinalReceive(damage * getMultiplier(GameUtilities.getPCLCardAffinityLevel(card, affinity, amount < 0)), type, card);
    }

    public float getMultiplier(int level) {
        return 1 - level * amount / 100f;
    }

    @Override
    public String getUpdatedDescription() {
        if (affinity == null) {
            return super.getUpdatedDescription();
        }
        if (this.amount < 0) {
            return this.formatDescription(1, this.amount * -1, affinity.getAffinitySymbol());
        }
        return this.formatDescription(0, this.amount, affinity.getAffinitySymbol());
    }
}

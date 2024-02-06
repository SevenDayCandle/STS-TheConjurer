package pinacolada.powers.conjurer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.powers.PCLPower;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.resources.pcl.PCLCoreImages;
import pinacolada.utilities.GameUtilities;

public class AffinityPropertyPower extends PCLPower {
    public static final int MULT = 10;
    public static final PCLPowerData DATA = register(AffinityPropertyPower.class, ConjurerResources.conjurer)
            .setType(PowerType.BUFF)
            .setLimits(-9999, 10)
            .setEndTurnBehavior(PCLPowerData.Behavior.Permanent);
    public final PCLAffinity affinity;

    public AffinityPropertyPower(PCLAffinity affinity, AbstractCreature owner, AbstractCreature source, int amount) {
        super(DATA, owner, source, amount);
        this.affinity = affinity;
        this.ID = DATA.ID + this.affinity;
    }

    @Override
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        return super.atDamageFinalReceive(damage * getMultiplier(GameUtilities.getPCLCardAffinityLevel(card, affinity, amount < 0)), type, card);
    }

    public float getMultiplier(int level) {
        return 1 + level * amount * MULT;
    }

    @Override
    public String getUpdatedDescription() {
        if (this.amount < 0) {
            return this.formatDescription(1, this.amount * -MULT, affinity.getAffinitySymbol());
        }
        return this.formatDescription(0, this.amount * MULT, affinity.getAffinitySymbol());
    }

    @Override
    protected void setupImages() {
        super.setupImages();
        if (this.region48 == null && this.img == PCLCoreImages.CardAffinity.unknown.texture()) {
            this.img = affinity.getIcon();
        }
    }
}

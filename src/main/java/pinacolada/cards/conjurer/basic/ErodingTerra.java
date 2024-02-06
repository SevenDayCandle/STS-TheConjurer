package pinacolada.cards.conjurer.basic;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.interfaces.subscribers.OnSpecificPowerActivatedSubscriber;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.powers.conjurer.ForgingPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class ErodingTerra extends PCLCard {
    public static final PCLCardData DATA = register(ErodingTerra.class, ConjurerResources.conjurer)
            .setPower(3, CardRarity.RARE)
            .setAffinities(2, PCLAffinity.Orange)
            .setCostUpgrades(-1)
            .setMaxCopies(1)
            .setCore();

    public ErodingTerra() {
        super(DATA);
    }

    public void setup(Object input) {
        addSpecialPower(0, ErodingTerraPower::new, 1);
    }

    public static class ErodingTerraPower extends PSpecialCardPower implements OnSpecificPowerActivatedSubscriber {
        public static final PCLPowerData PDATA = createFromCard(ErodingTerraPower.class, DATA);

        public ErodingTerraPower(AbstractCreature owner, AbstractCreature source, PSkill<?> move) {
            super(PDATA, owner, source, move);
        }

        @Override
        public boolean onPowerActivated(AbstractPower power, AbstractCreature abstractCreature, boolean originalValue) {
            if (power instanceof ForgingPower && ForgingPower.targetCard != null) {
                GameUtilities.retain(ForgingPower.targetCard);
                GameUtilities.modifyTag(ForgingPower.targetCard, PCLCardTag.Recast, move.amount, true);
            }
            return originalValue;
        }
    }
}

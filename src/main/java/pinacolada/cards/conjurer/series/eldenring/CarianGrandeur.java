package pinacolada.cards.conjurer.series.eldenring;


import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.interfaces.subscribers.OnSpecificPowerActivatedSubscriber;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.powers.common.WardingPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;

@VisibleCard
public class CarianGrandeur extends PCLCard {
    public static final PCLCardData DATA = register(CarianGrandeur.class, ConjurerResources.conjurer)
            .setPower(2, CardRarity.RARE)
            .setCostUpgrades(-1)
            .setAffinities(1, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public CarianGrandeur() {
        super(DATA);
    }

    public void setup(Object input) {
        addSpecialPower(0, CarianGrandeurPower::new, 1);
    }

    public static class CarianGrandeurPower extends PSpecialCardPower implements OnSpecificPowerActivatedSubscriber {
        public static final PCLPowerData PDATA = createFromCard(CarianGrandeurPower.class, DATA);

        public CarianGrandeurPower(AbstractCreature owner, AbstractCreature source, PSkill<?> move) {
            super(PDATA, owner, source, move);
        }

        @Override
        public boolean onPowerActivated(AbstractPower power, AbstractCreature abstractCreature, boolean originalValue) {
            if (power instanceof VigorPower) {
                PCLActions.bottom.applyPower(owner, WardingPower.DATA, move.amount * power.amount / 2);
            }
            else if (power instanceof WardingPower) {
                PCLActions.bottom.applyPower(owner, PCLPowerData.Vigor, move.amount * power.amount / 2);
            }
            return originalValue;
        }
    }
}

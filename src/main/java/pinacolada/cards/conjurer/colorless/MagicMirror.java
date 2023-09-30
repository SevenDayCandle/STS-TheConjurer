package pinacolada.cards.conjurer.colorless;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.ConjurerEFK;
import pinacolada.effects.PCLEffects;
import pinacolada.interfaces.subscribers.OnTryApplyPowerSubscriber;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class MagicMirror extends PCLCard {
    public static final PCLCardData DATA = register(MagicMirror.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE, PCLCardTarget.Self)
            .setBlock(2, 0)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Blue)
            .setCore(true);

    public MagicMirror() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
        addSpecialPower(0, (s, i) -> new MagicMirrorPower(i.source, i.source, s), 2).setUpgrade(1);
    }

    public static class MagicMirrorPower extends PSpecialCardPower implements OnTryApplyPowerSubscriber {
        public static final PCLPowerData PDATA = createFromCard(MagicMirrorPower.class, DATA);

        public MagicMirrorPower(AbstractCreature owner, AbstractCreature source, PSkill<?> move) {
            super(PDATA, owner, source, move);
        }

        @Override
        public void onInitialApplication() {
            super.onInitialApplication();
            PCLEffects.Queue.playEFX(ConjurerEFK.MGC_W2_Shield_OnHit);
        }

        @Override
        public boolean tryApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source, AbstractGameAction action) {
            if ((power.owner == owner || target == owner) && source != null) {
                boolean isDebuff = GameUtilities.isDebuff(power);
                if (isDebuff && amount > 0) {
                    reducePower(1);
                    if (amount <= 0) {
                        removePower();
                    }

                    flash();
                    // Only actually reflect basic debuffs because modded debuffs on enemies may cause crashes
                    if (GameUtilities.isPCLPower(power)) {
                        action.target = source;
                        power.owner = source;
                    }
                    else {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}

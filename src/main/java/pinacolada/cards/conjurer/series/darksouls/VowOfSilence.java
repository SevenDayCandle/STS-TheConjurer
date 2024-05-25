package pinacolada.cards.conjurer.series.darksouls;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import extendedui.EUIUtils;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.dungeon.CombatManager;
import pinacolada.interfaces.subscribers.OnTryApplyPowerSubscriber;
import pinacolada.patches.power.FrailPowerPatches;
import pinacolada.patches.power.VulnerablePowerPatches;
import pinacolada.patches.power.WeakPowerPatches;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.utilities.GameUtilities;

import java.util.HashMap;

@VisibleCard
public class VowOfSilence extends PCLCard {
    public static final PCLCardData DATA = register(VowOfSilence.class, ConjurerResources.conjurer)
            .setPower(0, CardRarity.RARE)
            .setTags(PCLCardTag.Exhaust.make(), PCLCardTag.Ethereal.make(1, 0))
            .setAffinities(PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public VowOfSilence() {
        super(DATA);
    }

    public void setup(Object input) {
        addSpecialPower(0, VowOfSilencePower::new, 5, 1);
    }

    public static class VowOfSilencePower extends PSpecialCardPower implements OnTryApplyPowerSubscriber {
        public static final PCLPowerData PDATA = createFromCard(VowOfSilencePower.class, DATA);
        protected final HashMap<String, Integer> recordedPowers = new HashMap<>();

        public VowOfSilencePower(AbstractCreature owner, AbstractCreature source, PSkill<?> move) {
            super(PDATA, owner, source, move);
        }

        @Override
        public void onAfterCardPlayed(AbstractCard c) {
            super.onAfterCardPlayed(c);
            if (c != null && c.type == CardType.POWER) {
                reducePower(1);
            }
        }

        @Override
        public void onRemove() {
            super.onRemove();
            for (String powerID : recordedPowers.keySet()) {
                int amount = recordedPowers.getOrDefault(powerID, 0);
                PCLPowerData ph = PCLPowerData.getStaticData(powerID);
                if (ph != null && amount != 0) {
                    PCLActions.bottom.applyPower(owner, ph, amount);
                }
            }
        }

        @Override
        public boolean tryApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source, AbstractGameAction action) {
            if (!GameUtilities.isPCLBuff(power)) {
                return true;
            }
            recordedPowers.merge(power.ID, amount, Integer::sum);
            return false;
        }
    }
}

package pinacolada.cards.conjurer.basic;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.actions.PCLActions;
import pinacolada.actions.affinity.AddAffinityLevel;
import pinacolada.annotations.VisibleCard;
import pinacolada.cardmods.PermanentBlockModifier;
import pinacolada.cardmods.PermanentDamageModifier;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.dungeon.CombatManager;
import pinacolada.dungeon.ConjurerElementButton;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.effects.vfx.ScreenLeavesEffect;
import pinacolada.interfaces.subscribers.OnIntensifySubscriber;
import pinacolada.interfaces.subscribers.OnSpecificPowerActivatedSubscriber;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.powers.conjurer.ElementPowerData;
import pinacolada.powers.conjurer.FlowPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PSkill;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.skills.skills.PTrigger;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class Ecosystem extends PCLCard {
    public static final PCLCardData DATA = register(Ecosystem.class, ConjurerResources.conjurer)
            .setPower(1, CardRarity.RARE)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green, PCLAffinity.Orange)
            .setMaxCopies(1)
            .setCore();

    public Ecosystem() {
        super(DATA);
    }

    public void setup(Object input) {
        addSpecialPower(0, (s, i) -> new EcosystemPower(i.source, i.source, s), 10, 1);
    }

    public static class EcosystemPower extends PSpecialCardPower implements OnIntensifySubscriber {
        public static final PCLPowerData PDATA = createFromCard(EcosystemPower.class, DATA);

        public EcosystemPower(AbstractCreature owner, AbstractCreature source, PSkill<?> move) {
            super(PDATA, owner, source, move);
        }

        @Override
        public void onIntensify(PCLAffinity affinity) {
            ConjurerElementButton button = ConjurerReactionMeter.meter.getElementButton(affinity);
            if (button != null && button.getLevel() >= move.amount) {
                int actualAmount = move.refreshAmount(move.getInfo(null));
                PCLActions.bottom.callback(new AddAffinityLevel(affinity, -actualAmount), __ -> {
                    for (AbstractCreature target : GameUtilities.getAllCharacters(true)) {
                        PCLActions.bottom.applyPower(owner, target, button.power, move.extra);
                    }
                });
            }
        }
    }
}

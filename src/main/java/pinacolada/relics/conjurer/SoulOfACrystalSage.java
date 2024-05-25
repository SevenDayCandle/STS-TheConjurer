package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.dungeon.AffinityReactions;
import pinacolada.dungeon.ConjurerElementButton;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.interfaces.subscribers.OnElementReactSubscriber;
import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;

import java.util.HashMap;
import java.util.Map;

@VisibleRelic
public class SoulOfACrystalSage extends PCLRelic implements OnElementReactSubscriber {
    public static final PCLRelicData DATA = register(SoulOfACrystalSage.class, ConjurerResources.conjurer)
            .setProps(RelicTier.STARTER, LandingSound.SOLID)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public SoulOfACrystalSage() {
        super(DATA);
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();
        subscribeToAll();
        setCounter(getValue());
    }

    @Override
    public int getValue() {
        return 2;
    }

    @Override
    public boolean onElementReact(PCLUseInfo info, AffinityReactions reactions, AbstractCreature mo) {
        boolean ret;
        if (counter > 0) {
            for (AbstractPower po : mo.powers) {
                for (ConjurerElementButton button : ConjurerReactionMeter.meter.getElementButtons()) {
                    if (button.matchesPower(po.ID)) {
                        HashMap<PCLAffinity, Integer> values = reactions.getReactants(button.power.affinity, po.owner);
                        if (values != null && !values.isEmpty()) {
                            counter -= 1;
                            flash();
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
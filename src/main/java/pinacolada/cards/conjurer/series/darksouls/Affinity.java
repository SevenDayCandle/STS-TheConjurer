package pinacolada.cards.conjurer.series.darksouls;


import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import extendedui.EUIUtils;
import pinacolada.actions.PCLActions;
import pinacolada.actions.powers.ElementReaction;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.dungeon.*;
import pinacolada.interfaces.subscribers.OnCardPlayedSubscriber;
import pinacolada.interfaces.subscribers.OnSpecificPowerActivatedSubscriber;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.powers.common.WardingPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.utilities.GameUtilities;

import java.util.ArrayList;

@VisibleCard
public class Affinity extends PCLCard {
    public static final PCLCardData DATA = register(Affinity.class, ConjurerResources.conjurer)
            .setPower(1, CardRarity.RARE)
            .setRTags(PCLCardTag.Ethereal)
            .setAffinities(2, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public Affinity() {
        super(DATA);
    }

    public void setup(Object input) {
        addSpecialPower(0, AffinityPower::new, 2, 1);
    }

    public static class AffinityPower extends PSpecialCardPower {
        public static final PCLPowerData PDATA = createFromCard(AffinityPower.class, DATA);

        public AffinityPower(AbstractCreature owner, AbstractCreature source, PSkill<?> move) {
            super(PDATA, owner, source, move);
        }

        @Override
        public void onPlayCard(AbstractCard c, AbstractMonster m) {
            super.onPlayCard(c, m);
            if (EUIUtils.all(ConjurerReactionMeter.meter.getElementButtons(), but -> GameUtilities.getPCLCardAffinityLevel(c, but.power.affinity, true) == 0)) {
                PCLUseInfo info = CombatManager.getLastInfo();
                AffinityReactions reactions = new AffinityReactions();

                ConjurerElementButton button = GameUtilities.getRandomElement(ConjurerReactionMeter.meter.getElementButtons());
                PCLAffinity target = GameUtilities.getRandomElement(new ArrayList<>(button.getReactAffinities()));

                for (AbstractCreature cr : info.targets) {
                    reactions.addReaction(button.power.affinity, target, cr, move.extra);
                }
                PCLActions.bottom.add(new ElementReaction(reactions, c, info));
            }
        }
    }
}

package pinacolada.cards.conjurer.series.touhouproject;


import com.megacrit.cardcrawl.cards.AbstractCard;
import extendedui.EUIUtils;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.CombatManager;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.monsters.PCLCardAlly;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.skills.PSpecialSkill;

@VisibleCard
public class MiracleMallet extends PCLCard {
    public static final PCLCardData DATA = register(MiracleMallet.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.RARE, PCLCardTarget.SingleAlly)
            .setAffinities(PCLAffinity.Star)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public MiracleMallet() {
        super(DATA);
    }

    public void action(PSpecialSkill move, PCLUseInfo info, PCLActions order) {
        order.withdrawAlly(EUIUtils.safeCast(info.target, PCLCardAlly.class))
                .setDestination(CombatManager.PURGED_CARDS)
                .setTriggerTimes(0)
                .showEffect(true)
                .addCallback(cards ->
                {
                    for (AbstractCard c : cards) {
                        if (c.costForTurn >= 0) {
                            order.gainEnergy(c.costForTurn + move.amount);
                        }
                    }
                });
    }

    public void setup(Object input) {
        addSpecialMove(0, this::action, 0).setUpgrade(1);
    }
}

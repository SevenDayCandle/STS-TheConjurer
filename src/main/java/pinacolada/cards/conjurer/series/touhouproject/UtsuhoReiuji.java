package pinacolada.cards.conjurer.series.touhouproject;


import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import extendedui.EUIUtils;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardAffinities;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.CombatManager;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.ElementPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class UtsuhoReiuji extends PCLCard {
    public static final PCLCardData DATA = register(UtsuhoReiuji.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Ranged, PCLCardTarget.Single)
            .setDamage(4, 1)
            .setHp(6, 2)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public UtsuhoReiuji() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.FIRE);
        addUseMove(PCond.onSummon(), getSpecialMove(0, this::specialMove, 1).setUpgrade(0).setTarget(PCLCardTarget.All));
    }

    public void specialMove(PSpecialSkill move, PCLUseInfo info, PCLActions order) {
        PCLActions.bottom.withdrawAlly(EUIUtils.filter(GameUtilities.getSummons(true), a -> a != move.getOwnerCreature()), CombatManager.summons.triggerTimes)
                .addCallback(cards -> {
                    for (AbstractCard c : cards) {
                        PCLCardAffinities cardAffinities = GameUtilities.getPCLCardAffinities(c);
                        if (cardAffinities != null) {
                            for (PCLAffinity aff : cardAffinities.getAffinities(true, true)) {
                                ElementPowerData debuff = ElementPowerData.get(aff);
                                if (debuff != null) {
                                    for (AbstractCreature target : move.getTargetList(info)) {
                                        order.applyPower(info.source, target, debuff, move.amount);
                                    }
                                }
                            }
                        }
                    }
                });
    }
}

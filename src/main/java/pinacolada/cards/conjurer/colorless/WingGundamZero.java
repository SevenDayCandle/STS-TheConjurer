package pinacolada.cards.conjurer.colorless;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import extendedui.EUIUtils;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardSelection;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class WingGundamZero extends PCLCard {
    public static final PCLCardData DATA = register(WingGundamZero.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.RARE, PCLAttackType.Ranged)
            .setDamage(6, array(3, 0))
            .setHp(11, 1)
            .setAffinities(PCLAffinity.Yellow, PCLAffinity.Silver)
            .setLoadout(ConjurerPlayerData.mobileSuitGundam, true);

    public WingGundamZero() {
        super(DATA);
    }

    @Override
    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SMALL_LASER);
        addGainPower(PTrigger.when(PCond.onTurnStart(), getSpecialMove(0, this::specialMove, 1, 1).setUpgrade(0, 1)));
    }

    public void specialMove(PSpecialSkill move, PCLUseInfo info, PCLActions order) {
        PCLActions.bottom.selectFromPile(name, move.amount, AbstractDungeon.player.hand)
                .setOrigin(PCLCardSelection.Top)
                .addCallback((cards) -> {
                    for (AbstractCard c : cards) {
                        PCLCard pC = EUIUtils.safeCast(c, PCLCard.class);
                        if (pC != null && pC.maxUpgrades() > 0) {
                            pC.upgradeLevelIncrease += move.extra;
                        }
                        c.upgrade();
                    }
                });
    }
}
package pinacolada.cards.conjurer.basic;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardAffinities;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.powers.conjurer.ElementPowerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class Ecosystem extends PCLCard {
    public static final PCLCardData DATA = register(Ecosystem.class, ConjurerResources.conjurer)
            .setPower(1, CardRarity.RARE)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green, PCLAffinity.Orange)
            .setCostUpgrades(-1)
            .setMaxCopies(1)
            .setCore();

    public Ecosystem() {
        super(DATA);
    }

    public void setup(Object input) {
        addSpecialPower(0, EcosystemPower::new, 1);
    }

    public static class EcosystemPower extends PSpecialCardPower {
        public static final PCLPowerData PDATA = createFromCard(EcosystemPower.class, DATA);

        public EcosystemPower(AbstractCreature owner, AbstractCreature source, PSkill<?> move) {
            super(PDATA, owner, source, move);
        }

        @Override
        public void atEndOfTurn(boolean isPlayer) {
            super.atEndOfTurn(isPlayer);
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                PCLCardAffinities cardAffinities = GameUtilities.getPCLCardAffinities(c);
                if (cardAffinities != null) {
                    for (PCLAffinity aff : cardAffinities.getAffinities(true, true)) {
                        ElementPowerData debuff = ElementPowerData.getElement(aff);
                        if (debuff != null) {
                            for (AbstractMonster mo : GameUtilities.getEnemies(true)) {
                                PCLActions.bottom.applyPower(owner, mo, debuff, amount);
                            }
                        }
                    }
                }
            }
        }
    }
}

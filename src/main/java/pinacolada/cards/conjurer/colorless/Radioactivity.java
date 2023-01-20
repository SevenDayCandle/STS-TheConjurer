package pinacolada.cards.conjurer.colorless;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.monsters.PCLCardAlly;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class Radioactivity extends PCLCard
{
    public static final PCLCardData DATA = register(Radioactivity.class, ConjurerResources.conjurer)
            .setPower(1, CardRarity.UNCOMMON)
            .setCostUpgrades(-1)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setColorless();

    public Radioactivity()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addSpecialPower(0, (s, i) -> new RadioactivityPower(i.source, s), 1);
    }

    public static class RadioactivityPower extends PSpecialCardPower
    {
        public RadioactivityPower(AbstractCreature owner, PSkill move)
        {
            super(DATA, owner, move);
        }

        public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
            if (power.type == PowerType.DEBUFF
                    && (source == owner || source instanceof PCLCardAlly)
                    && (move.target == PCLCardTarget.Self ^ !(owner == target)))
            {
                int applyAmount = Math.max(1, Math.abs(power.amount));
                for (AbstractMonster mo : GameUtilities.getEnemies(true))
                {
                    PCLActions.bottom.loseHP(source, mo, applyAmount, AbstractGameAction.AttackEffect.POISON);
                }
                flash();
            }
        }
    }
}

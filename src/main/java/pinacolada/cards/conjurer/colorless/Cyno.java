package pinacolada.cards.conjurer.colorless;


import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.interfaces.subscribers.OnAllyDeathSubscriber;
import pinacolada.interfaces.subscribers.OnMonsterDeathSubscriber;
import pinacolada.misc.CombatManager;
import pinacolada.monsters.PCLCardAlly;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.utilities.GameUtilities;

public class Cyno extends PCLCard
{
    public static final PCLCardData DATA = register(Cyno.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON)
            .setDamage(4, 1)
            .setPriority(1)
            .setHp(7, 1)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Orange, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.genshinImpact)
            .setColorless();

    public Cyno()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.SLASH_HEAVY);
        addSpecialPower(0, (s, i) -> new CynoPower(i.source, s), 3).setUpgrade(1);
    }

    public static class CynoPower extends PSpecialCardPower implements OnAllyDeathSubscriber, OnMonsterDeathSubscriber
    {
        public CynoPower(AbstractCreature owner, PSkill move)
        {
            super(DATA, owner, move);
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatManager.onAllyDeath.subscribe(this);
            CombatManager.onMonsterDeath.subscribe(this);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatManager.onAllyDeath.unsubscribe(this);
            CombatManager.onMonsterDeath.unsubscribe(this);
        }

        @Override
        public void onAllyDeath(PCLCard pclCard, PCLCardAlly pclCardAlly)
        {
            act();
        }

        @Override
        public void onMonsterDeath(AbstractMonster abstractMonster, boolean b)
        {
            act();
        }

        protected void act()
        {
            if (owner instanceof PCLCardAlly)
            {
                PCLCard card = ((PCLCardAlly) owner).card;
                if (card != null)
                {
                    GameUtilities.modifyDamage(card, move.amount, false);
                    flash();
                }
            }
            else
            {
                PCLActions.bottom.applyPower(new StrengthPower(owner, 2));
                flash();
            }
        }
    }
}

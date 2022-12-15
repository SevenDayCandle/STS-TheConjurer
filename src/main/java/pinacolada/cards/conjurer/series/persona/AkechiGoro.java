package pinacolada.cards.conjurer.series.persona;

import com.megacrit.cardcrawl.cards.AbstractCard;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PCustomCond;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.CardSelection;

public class AkechiGoro extends PCLCard
{
    public static final PCLCardData DATA = register(AkechiGoro.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.RARE, PCLAttackType.Normal, PCLCardTarget.RandomEnemy)
            .setDamage(7, 0)
            .setTags(PCLCardTag.Exhaust)
            .setUTags(PCLCardTag.Haste)
            .setAffinities(PCLAffinity.Orange, PCLAffinity.Light, PCLAffinity.Dark)
            .setLoadout(ConjurerPlayerData.persona);

    public AkechiGoro()
    {
        super(DATA);
    }

    public void action(PSpecialSkill move, PCLUseInfo info)
    {
        PCLActions.bottom.exhaustFromPile(name, move.amount, player.drawPile)
                .setOrigin(CardSelection.Bottom)
                .addCallback(cards -> {
                    move.cards = cards;
                    for (AbstractCard c : cards)
                    {
                        int amount = c.costForTurn < 0 ? move.extra : c.costForTurn;
                        PCLActions.bottom.gain(PCLPowerHelper.Vitality, amount);
                        PCLActions.bottom.gain(PCLPowerHelper.DelayedDamage, amount);
                    }
                });
    }

    public void setup(Object input)
    {
        addDamageMove().setDamageEffect(PCLEffekseerEFX.SWORD08);
        addSpecialMove(0, this::action, 3, 2).setUpgrade(1);
        addUseMove(new AkechiGoroCond(DATA, 7), PMove.obtain(3, Curse_GriefSeed.DATA));
    }

    protected static class AkechiGoroCond extends PCustomCond
    {
        public AkechiGoroCond(PCLCardData data, int amount)
        {
            super(data, 1, amount);
        }

        protected void useImpl(PCLUseInfo info)
        {
            if (source.getEffects().size() > 0)
            {
                int cost = 0;
                for (AbstractCard c : source.getEffect(0).getLowestChild().cards)
                {
                    cost += c.costForTurn < 0 ? source.getEffect(0).getLowestChild().extra : c.costForTurn;
                    if (cost >= amount && getChild() != null && info.tryActivateLimited())
                    {
                        getChild().use(info);
                    }
                }
            }
        }
    }

}

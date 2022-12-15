package pinacolada.cards.conjurer.series.persona;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import extendedui.EUIUtils;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.*;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PSpecialSkill;

public class Joker extends PCLCard
{
    public static final PCLCardData DATA = register(Joker.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.RARE, PCLAttackType.Ranged)
            .setDamage(2, 3)
            .setMaxCopies(1)
            .setAffinities(PCLAffinity.Star)
            .setLoadout(ConjurerPlayerData.persona);

    public Joker()
    {
        super(DATA);
    }

    public void action(PSpecialSkill move, PCLUseInfo info)
    {
        PCLActions.bottom.selectFromPile(name, move.amount, player.discardPile)
                .setFilter(c -> AbstractDungeon.actionManager.cardsPlayedThisTurn.contains(c) && !cardID.equals(c.cardID))
                .addCallback(cards -> {
                    for (AbstractCard c : cards)
                    {
                        PCLActions.bottom.playCard(c, player.discardPile, EUIUtils.safeCast(info.target, AbstractMonster.class));
                    }
                });
    }

    public void setup(Object input)
    {
        addDamageMove().setDamageEffect(PCLEffekseerEFX.GUN01);
        addSpecialMove(0, this::action, 1);
        addUseMove(PCond.combust(), PMove.selfTransform(Joker_Arsene.DATA));
    }
}

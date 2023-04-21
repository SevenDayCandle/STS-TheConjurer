package pinacolada.cards.conjurer.series.touhouproject;


import com.megacrit.cardcrawl.cards.AbstractCard;
import extendedui.EUIUtils;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardAffinities;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.GameUtilities;

import java.util.Arrays;
import java.util.HashSet;

@VisibleCard
public class UtsuhoReiuji extends PCLCard
{
    public static final PCLCardData DATA = register(UtsuhoReiuji.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Ranged, PCLCardTarget.RandomEnemy)
            .setDamage(5, 0)
            .setPriority(1)
            .setHp(7, 2)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public UtsuhoReiuji()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.FIRE);
        addUseMove(PCond.onSummon(), getSpecialMove(0, this::specialMove, 4).setUpgrade(1).setTarget(PCLCardTarget.All));
    }

    public void specialMove(PSpecialSkill move, PCLUseInfo info)
    {
        HashSet<PCLAffinity> available = new HashSet<>(Arrays.asList(PCLAffinity.getAvailableAffinities()));
        available.add(PCLAffinity.Star);

        PCLActions.bottom.withdrawAlly(EUIUtils.filter(GameUtilities.getSummons(true), a -> a != move.getOwnerCreature()))
                .addCallback(cards -> {
                    for (AbstractCard c : cards)
                    {
                        PCLCardAffinities cardAffinities = GameUtilities.getPCLCardAffinities(c);
                        if (cardAffinities != null)
                        {
                            for (PCLAffinity aff : cardAffinities.getAffinities(false, false))
                            {
                                if (available.contains(aff))
                                {
                                    PCLElementHelper debuff = PCLElementHelper.get(aff);
                                    if (debuff != null)
                                    {
                                        move.getActions().applyPower(info.source, info.target, move.target, debuff, move.amount);
                                    }
                                }
                            }
                        }
                    }
                });
    }
}
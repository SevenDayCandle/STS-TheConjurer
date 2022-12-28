package pinacolada.cards.conjurer.basic;


import com.megacrit.cardcrawl.cards.AbstractCard;
import pinacolada.actions.PCLActions;
import pinacolada.actions.powers.ApplyElementalDebuff;
import pinacolada.cards.base.*;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.skills.skills.base.moves.PMove_Scry;
import pinacolada.utilities.GameUtilities;

import java.util.Arrays;
import java.util.HashSet;

public class Entropy extends PCLCard
{
    public static final PCLCardData DATA = register(Entropy.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Yellow, PCLAffinity.Purple)
            .setCore();

    public Entropy()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(new PMove_Scry(3).setUpgrade(1, 0), getSpecialMove(0, this::specialMove, 2).setUpgrade(0, 1).setTarget(PCLCardTarget.Single));
    }

    public void specialMove(PSpecialSkill move, PCLUseInfo info)
    {
        HashSet<PCLAffinity> available = new HashSet<>(Arrays.asList(PCLAffinity.getAvailableAffinities()));
        available.add(PCLAffinity.Star);

        for (AbstractCard c : move.cards)
        {
            PCLCardAffinities cardAffinities = GameUtilities.getPCLCardAffinities(c);
            if (cardAffinities != null)
            {
                for (PCLAffinity aff : cardAffinities.getAffinities(false))
                {
                    if (available.contains(aff))
                    {
                        PCLElementHelper debuff = PCLElementHelper.get(aff);
                        if (debuff != null)
                        {
                            PCLActions.bottom.add(new ApplyElementalDebuff(info.source, info.target, move.target, debuff, move.amount));
                        }
                    }
                }
            }
        }
    }
}

package pinacolada.cards.conjurer.series.touhouproject;

import com.megacrit.cardcrawl.cards.AbstractCard;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.*;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.skills.skills.base.moves.PMove_Scry;
import pinacolada.utilities.GameUtilities;

import java.util.Arrays;
import java.util.HashSet;

public class KaguyaHouraisan extends PCLCard
{
    public static final PCLCardData DATA = register(KaguyaHouraisan.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE, PCLCardTarget.Single)
            .setMagicNumber(1, 0)
            .setAffinities(PCLAffinity.Blue)
            .setMultiformData(2)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public KaguyaHouraisan()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(new PMove_Scry(3).setUpgrade(1, 0), getSpecialMove(0, this::specialMove, 2).setUpgrade(0, 1).setTarget(PCLCardTarget.Single));
        addUseMove(PCond.limited(), PCond.pileHas(0, PCLCardGroupHelper.DrawPile), PMove.gain(2, PCLPowerHelper.Focus));
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
                        PCLPowerHelper debuff = aff.getElementPower();
                        if (debuff != null)
                        {
                            PCLActions.bottom.applyPower(move.target, debuff, move.amount);
                        }
                    }

                }
            }

        }
    }
}

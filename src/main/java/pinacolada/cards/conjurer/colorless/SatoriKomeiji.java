package pinacolada.cards.conjurer.colorless;

import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.FrozenEye;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.misc.CombatManager;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PTrigger;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.GameUtilities;

public class SatoriKomeiji extends PCLCard
{
    public static final PCLCardData DATA = register(SatoriKomeiji.class, ConjurerResources.conjurer)
            .setPower(3, CardRarity.RARE)
            .setAffinities(PCLAffinity.Blue)
            .setColorless()
            .setTags(PCLCardTag.Delayed)
            .setUTags(PCLCardTag.Retain)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public SatoriKomeiji()
    {
        super(DATA);
    }

    public void action(PSpecialSkill move, PCLUseInfo info)
    {
        if (CombatManager.getCombatData(FrozenEye.ID, false))
        {
            PCLActions.bottom.applyPower(PCLCardTarget.Self, PCLPowerHelper.Artifact, move.amount);
        }
        else
        {
            CombatManager.setCombatData(FrozenEye.ID, true);
        }
    }

    public void setup(Object input)
    {
        addGainPower(PTrigger.when(PCond.onTurnStart(), getSpecialMove(0, this::specialMove, 1).setUpgrade(1)));
        addSpecialMove(1, this::action, 2);
    }

    public void specialMove(PSpecialSkill move, PCLUseInfo info)
    {
        if (player.drawPile.size() == 1)
        {
            return;
        }

        PCLActions.top.selectFromPile(name, move.amount, player.drawPile)
                .addCallback(cards ->
                {
                    for (AbstractCard c : cards)
                    {
                        player.drawPile.removeCard(c);
                        player.drawPile.addToTop(c);
                    }

                    GameUtilities.refreshHandLayout();
                });

        if (player.drawPile.isEmpty() && player.discardPile.size() > 0)
        {
            PCLActions.top.add(new EmptyDeckShuffleAction());
        }
    }

}

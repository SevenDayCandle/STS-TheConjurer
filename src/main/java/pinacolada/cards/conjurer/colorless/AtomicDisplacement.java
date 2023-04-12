package pinacolada.cards.conjurer.colorless;


import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.GameUtilities;

import java.util.ArrayList;

@VisibleCard
public class AtomicDisplacement extends PCLCard
{
    public static final PCLCardData DATA = register(AtomicDisplacement.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Yellow)
            .setCore(true);

    public AtomicDisplacement()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addSpecialMove(0, this::action, 5).setUpgrade(2);
    }

    public void action(PSpecialSkill move, PCLUseInfo info)
    {
        ArrayList<AbstractPower> playerPowers = new ArrayList<>();
        ArrayList<AbstractPower> enemyPowers = new ArrayList<>();
        for (AbstractPower po : info.source.powers)
        {
            if (GameUtilities.isDebuff(po) && GameUtilities.isCommonPower(po))
            {
                playerPowers.add(po);
                PCLActions.bottom.removePower(info.source, po);
            }
        }
        for (AbstractPower po : info.target.powers)
        {
            if (GameUtilities.isDebuff(po) && GameUtilities.isCommonPower(po))
            {
                enemyPowers.add(po);
                PCLActions.bottom.removePower(info.target, po);
            }
        }

        for (AbstractPower po : playerPowers)
        {
            po.owner = info.target;
            PCLActions.bottom.applyPower(info.source, info.target, po);
        }
        for (AbstractPower po : enemyPowers)
        {
            po.owner = info.source;
            PCLActions.bottom.applyPower(info.source, info.source, po);
        }

        int gained = enemyPowers.size() - playerPowers.size();
        int thp = gained * move.amount;
        if (thp > 0)
        {
            PCLActions.bottom.gainTemporaryHP(thp);
        }
    }
}

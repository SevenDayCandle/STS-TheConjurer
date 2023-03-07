package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleRelic;
import pinacolada.relics.pcl.DisguiseRelic;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;

@VisibleRelic
public class PeriodicTable extends DisguiseRelic
{
    public static final String ID = createFullID(ConjurerResources.conjurer, PeriodicTable.class);

    public PeriodicTable()
    {
        super(ID, RelicTier.STARTER, LandingSound.SOLID);
    }

    @Override
    public void atBattleStart()
    {
        PCLActions.bottom.draw(1).setFilter(f -> f.type == PCLEnum.CardType.SUMMON).addCallback(cards -> {
            for (AbstractCard c : cards)
            {
                PCLActions.bottom.modifyCost(c, -getValue(), false, true);
            }
        });
    }

    public int getValue() {
        return 1;
    }
}
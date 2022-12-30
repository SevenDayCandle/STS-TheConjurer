package pinacolada.cards.conjurer.colorless;


import com.megacrit.cardcrawl.powers.StrengthPower;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLUseInfo;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.GameUtilities;

public class Enervate extends PCLCard
{
    public static final PCLCardData DATA = register(Enervate.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Purple)
            .setColorless();

    public Enervate()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addSpecialMove(0, this::action, 2).setUpgrade(1);
    }

    public void action(PSpecialSkill move, PCLUseInfo info)
    {
        int amount = GameUtilities.getPowerAmount(info.target, StrengthPower.POWER_ID) / 2;
        if (amount > 0)
        {
            PCLActions.bottom.reducePower(info.target, info.source, StrengthPower.POWER_ID, amount + move.amount);
        }
        else
        {
            PCLActions.bottom.reducePower(info.target, info.source, StrengthPower.POWER_ID, move.amount);
        }
    }
}

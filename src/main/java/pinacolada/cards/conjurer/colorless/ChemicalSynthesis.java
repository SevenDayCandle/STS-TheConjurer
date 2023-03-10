package pinacolada.cards.conjurer.colorless;

import com.megacrit.cardcrawl.potions.AbstractPotion;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.misc.PCLUseInfo;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.skills.PSpecialSkill;

@VisibleCard
public class ChemicalSynthesis extends PCLCard
{
    public static final PCLCardData DATA = register(ChemicalSynthesis.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Single)
            .setAffinities(PCLAffinity.Blue)
            .setTags(PCLCardTag.Purge)
            .setCostUpgrades(-1)
            .setColorless();

    public ChemicalSynthesis()
    {
        super(DATA);
    }

    @Override
    public void setup(Object input)
    {
        addSpecialMove(0, this::action, 2, 2);
    }

    public void action(PSpecialSkill move, PCLUseInfo info)
    {
        for (AbstractPotion potion : player.potions)
        {
            if (potion != null && potion.canUse())
            {
                PCLActions.bottom.usePotion(potion, info.target, move.amount);
                return;
            }
        }
        PCLActions.bottom.heal(move.extra);
    }
}
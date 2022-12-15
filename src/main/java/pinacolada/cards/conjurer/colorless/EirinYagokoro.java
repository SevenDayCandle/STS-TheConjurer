package pinacolada.cards.conjurer.colorless;

import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.PotionSlot;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.skills.skills.base.moves.PMove_RemovePower;

public class EirinYagokoro extends PCLCard
{
    public static final PCLCardData DATA = register(EirinYagokoro.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Single)
            .setHp(4, 0)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Light)
            .setTags(PCLCardTag.Purge)
            .setUTags(PCLCardTag.Retain)
            .setColorless()
            .setMaxCopies(1)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public EirinYagokoro()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(new PMove_RemovePower(PCLCardTarget.Self, PCLPowerHelper.Poison, PCLPowerHelper.Vulnerable).setCustomUpgrade((s, f, u) ->
            s.setPower(u > 0 ? array(PCLPowerHelper.Poison, PCLPowerHelper.Vulnerable, PCLPowerHelper.Weak) : array(PCLPowerHelper.Poison, PCLPowerHelper.Vulnerable))
        ));
        addUseMove(PCond.limited(), getSpecialMove(0, this::action, 1));
    }

    public void action(PSpecialSkill move, PCLUseInfo info)
    {
        for (AbstractPotion potion : player.potions)
        {
            if (!(potion instanceof PotionSlot) && potion.canUse())
            {
                PCLActions.bottom.usePotion(potion, info.target).setShouldRemove(false);
                PCLActions.bottom.usePotion(potion, info.target);
                return;
            }
        }
        PCLActions.bottom.makeCardInHand(this.makeStatEquivalentCopy());

    }
}

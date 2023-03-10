package pinacolada.cards.conjurer.special;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.misc.PCLUseInfo;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PSpecialSkill;

@VisibleCard
public class Apparition extends PCLCard
{
    public static final PCLCardData DATA = register(Apparition.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.SPECIAL, PCLCardTarget.None)
            .setAffinities(PCLAffinity.Purple)
            .setTags(PCLCardTag.Exhaust, PCLCardTag.Ethereal)
            .setCostUpgrades(-1)
            .setCore(true);

    public Apparition()
    {
        super(DATA);
    }

    public void action(PSpecialSkill move, PCLUseInfo info)
    {
        PCLActions.bottom.moveCards(AbstractDungeon.player.hand, AbstractDungeon.player.discardPile)
                .setFilter(c -> c instanceof Apparition)
                .showEffect(true, true, 0.25f)
                .addCallback(cards -> {
                    PCLActions.bottom.draw(cards.size());
                });
    }

    @Override
    public void setup(Object input)
    {
        addUseMove(PMove.apply(PCLCardTarget.All, 1, PCLPowerHelper.Intangible));
        addSpecialMove(0, this::action, 1);
    }
}
package pinacolada.cards.conjurer.basic;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import extendedui.EUIUtils;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.misc.PCLUseInfo;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.ui.combat.ConjurerReactionMeter;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class ErodingTerra extends PCLCard
{
    public static final PCLCardData DATA = register(ErodingTerra.class, ConjurerResources.conjurer)
            .setPower(3, CardRarity.RARE)
            .setAffinities(2, PCLAffinity.Orange)
            .setUTags(PCLCardTag.Retain)
            .setCore();

    public ErodingTerra()
    {
        super(DATA);
    }

    public void action(PSpecialSkill move, PCLUseInfo info)
    {
        PCLActions.bottom.applyPower(new ErodingTerraPower(info.source, move));
    }

    public void setup(Object input)
    {
        addSpecialMove(0, this::action, 1);
    }

    public static class ErodingTerraPower extends PSpecialCardPower
    {
        public ErodingTerraPower(AbstractCreature owner, PSkill move)
        {
            super(ErodingTerra.DATA, owner, move);
        }

        @Override
        public void onPlayCard(AbstractCard card, AbstractMonster m)
        {
            super.onPlayCard(card, m);
            if (card.costForTurn > 0)
            {
                PCLActions.bottom.gainBlock(card.costForTurn * move.amount *
                        EUIUtils.sumInt(GameUtilities.getAllCharacters(true),
                                c -> EUIUtils.sumInt(EUIUtils.filter(c.powers, po -> ConjurerReactionMeter.meter.isPowerElemental(po.ID, PCLAffinity.Orange)), po -> po.amount)));
                this.flash();
            }
        }
    }
}

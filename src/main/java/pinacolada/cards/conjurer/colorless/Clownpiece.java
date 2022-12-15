package pinacolada.cards.conjurer.colorless;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import extendedui.EUIUtils;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.powers.special.ProvokedPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.skills.skills.base.moves.PMove_PlayTop;
import pinacolada.utilities.GameUtilities;

public class Clownpiece extends PCLCard
{
    public static final PCLCardData DATA = register(Clownpiece.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.UNCOMMON)
            .setAffinities(PCLAffinity.Star)
            .setColorless()
            .setTags(
                    PCLCardTag.Autoplay.make(1, array(0, 1)),
                    PCLCardTag.Exhaust.make(1, array(0, 2))
            )
            .setMultiformData(2)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public Clownpiece()
    {
        super(DATA);
    }

    public void action(PSpecialSkill move, PCLUseInfo info)
    {
        if (move.cards != null)
        {
            AbstractCard card = EUIUtils.find(move.cards, c -> c.type == CardType.ATTACK);
            if (card != null)
            {
                for (AbstractMonster mp : GameUtilities.getEnemies(true))
                {
                    PCLActions.bottom.applyPower(info.source, new ProvokedPower(mp));
                }
            }
        }
    }

    public void setup(Object input)
    {
        addUseMove(new PMove_PlayTop(2, PCLCardGroupHelper.DrawPile), getSpecialMove(0, this::action, 1).useParent(true));
    }
}

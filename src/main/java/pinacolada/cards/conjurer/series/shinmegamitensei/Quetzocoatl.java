package pinacolada.cards.conjurer.series.shinmegamitensei;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import extendedui.EUIUtils;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLUseInfo;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PTrigger;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.ui.combat.ConjurerReactionMeter;
import pinacolada.utilities.GameUtilities;

public class Quetzocoatl extends PCLCard
{
    public static final PCLCardData DATA = register(Quetzocoatl.class, ConjurerResources.conjurer)
            .setPower(3, CardRarity.RARE)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Quetzocoatl()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addGainPower(PTrigger.interactable(PCond.onTurnEnd(), getSpecialMove(0, this::specialMove)));
    }

    public void specialMove(PSpecialSkill move, PCLUseInfo info)
    {
        for (AbstractMonster mo : GameUtilities.getEnemies(true))
        {
            int sum = EUIUtils.sumInt(mo.powers, po -> ConjurerReactionMeter.meter.isPowerElemental(po.ID) ? po.amount : 0);
            if (sum > 0)
            {
                PCLActions.bottom.loseHP(info.source, mo, sum, AttackEffects.POISON);
            }
        }
    }
}

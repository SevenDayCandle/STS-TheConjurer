package pinacolada.cards.conjurer.series.touhouproject;

import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLUseInfo;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.powers.PSkillPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.skills.PTrigger;
import pinacolada.skills.conjurer.conditions.PCond_PayReaction;
import pinacolada.skills.skills.PSpecialSkill;

public class KawashiroNitori extends PCLCard
{
    public static final PCLCardData DATA = register(KawashiroNitori.class, ConjurerResources.conjurer)
            .setPower(2, CardRarity.UNCOMMON)
            .setCostUpgrades(-1)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setTags(PCLCardTag.Retain)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public KawashiroNitori()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addGainPower(PTrigger.when(PCond.redox(), PMove.dealDamage(5)));
        addGainPower(PTrigger.interactable(new PCond_PayReaction(10), getSpecialMove(0, this::specialMove, 2, 4)));
    }

    public void specialMove(PSpecialSkill move, PCLUseInfo info)
    {
        String targetID = PSkillPower.createPowerID(getPowerEffect(0));
        String sourceID = PSkillPower.createPowerID(getPowerEffect(1));
        for (AbstractPower po : info.source.powers)
        {
            if (po instanceof PSkillPower)
            {
                if (targetID.equals(po.ID))
                {
                    for (PSkill tr : ((PSkillPower) po).ptriggers)
                    {
                        tr.getLowestChild().addAmountForCombat(move.amount);
                    }
                    po.flashWithoutSound();
                }
                else if (sourceID.equals(po.ID))
                {
                    for (PSkill tr : ((PSkillPower) po).ptriggers)
                    {
                        if (tr.getChild() instanceof PCond_PayReaction)
                        {
                            tr.getChild().addAmountForCombat(move.extra);
                        }
                    }
                    po.flashWithoutSound();
                }
            }
        }
    }
}

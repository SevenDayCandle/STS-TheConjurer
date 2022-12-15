package pinacolada.cards.conjurer.series.touhouproject;

import extendedui.EUIUtils;
import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.misc.CombatManager;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.DelayUse;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PCustomCond;
import pinacolada.skills.skills.PDelay;

public class YukariYakumo extends PCLCard
{
    public static final PCLCardData DATA = register(YukariYakumo.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE, PCLCardTarget.Self)
            .setCostUpgrades(-1)
            .setBlock(1, 0)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public YukariYakumo()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PDelay.turnStart(1), new YukariYakumoCond(DATA).setPower(PCLPowerHelper.Vulnerable, PCLPowerHelper.Weak, PCLPowerHelper.Frail));
        addUseMove(PCond.combust(), PMove.applyToEveryone(3, PCLPowerHelper.Vulnerable, PCLPowerHelper.Weak, PCLPowerHelper.Frail));
    }


    protected static class YukariYakumoCond extends PCustomCond
    {
        public YukariYakumoCond(PCLCardData data)
        {
            super(data);
        }

        @Override
        protected void useImpl(PCLUseInfo info)
        {
            for (PCLPowerHelper power : powers)
            {
                CombatManager.addPlayerEffectBonus(power.ID, -CombatManager.getEffectBonus(power.ID) * 2);
                DelayUse.schedule(1, DelayUse.Timing.StartOfTurnFirst,
                        info, (i) -> CombatManager.addPlayerEffectBonus(power.ID, CombatManager.getEffectBonus(power.ID) * 2)
                );
            }
        }

        @Override
        public String getSubText()
        {
            return EUIUtils.format(cardData.strings.EXTENDED_DESCRIPTION[descIndex], getPowerString());
        }
    }
}

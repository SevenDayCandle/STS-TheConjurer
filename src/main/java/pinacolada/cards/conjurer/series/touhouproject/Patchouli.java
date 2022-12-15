package pinacolada.cards.conjurer.series.touhouproject;

import extendedui.EUIUtils;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLUseInfo;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.skills.PTrigger;
import pinacolada.skills.conjurer.conditions.PCond_PayReaction;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.PSpecialSkill;

public class Patchouli extends PCLCard
{
    public static final PCLCardData DATA = register(Patchouli.class, ConjurerResources.conjurer)
            .setPower(3, CardRarity.RARE)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public Patchouli()
    {
        super(DATA);
    }

    protected PSkill getMove(PCLAffinity affinity, int amount)
    {
        return PMultiSkill.join(PMove.applyToEnemies(amount, affinity.getElementPower()));
    }

    public void setup(Object input)
    {
        addGainPower(PTrigger.when(PCond.onTurnStart(), getSpecialMove(0, this::specialMove, 5).setUpgrade(2)));
        addGainPower(PTrigger.interactable(new PCond_PayReaction(10).setUpgrade(-1), PMove.gain(1, PCLPowerHelper.Energized)));
    }

    public void specialMove(PSpecialSkill move, PCLUseInfo info)
    {

        move.getActions().tryChooseAffinitySkill(getName(), 1, info.source, null, EUIUtils.map(PCLAffinity.getAvailableAffinities(), a -> getMove(a, move.amount)));
    }

}

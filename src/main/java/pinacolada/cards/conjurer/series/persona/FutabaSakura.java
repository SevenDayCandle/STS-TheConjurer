package pinacolada.cards.conjurer.series.persona;

import pinacolada.actions.PCLActions;
import pinacolada.cards.base.*;
import pinacolada.misc.CombatManager;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PTrigger;
import pinacolada.skills.conjurer.conditions.PCond_Intensify;
import pinacolada.skills.conjurer.moves.PMove_GainReaction;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.skills.skills.base.conditions.PCond_PayEnergy;
import pinacolada.skills.skills.base.modifiers.PMod_IncreaseOnUse;
import pinacolada.ui.combat.ConjurerElementButton;

public class FutabaSakura extends PCLCard
{
    public static final PCLCardData DATA = register(FutabaSakura.class, ConjurerResources.conjurer)
            .setPower(1, CardRarity.RARE)
            .setAffinities(PCLAffinity.Blue)
            .setCostUpgrades(-1, 0)
            .setMultiformData(2)
            .setLoadout(ConjurerPlayerData.persona);

    public FutabaSakura()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addGainPower(PTrigger.when(new PCond_Intensify(), getSpecialMove(0, this::specialMove, 5).setUpgrade(0, 2).setTarget(PCLCardTarget.AllEnemy)));
        addGainPower(PTrigger.interactable(new PCond_PayEnergy(1), new PMod_IncreaseOnUse(3), new PMove_GainReaction(5)));
    }

    public void specialMove(PSpecialSkill move, PCLUseInfo info)
    {
        ConjurerElementButton button = info.getData(CombatManager.playerSystem.getElementButton(PCLAffinity.Light));
        PCLActions.bottom.applyPower(info.source, info.target, move.target, button.affinity.getElementPower(), move.amount);
    }
}

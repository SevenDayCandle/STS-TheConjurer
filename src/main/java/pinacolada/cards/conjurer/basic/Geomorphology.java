package pinacolada.cards.conjurer.basic;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Geomorphology extends PCLCard
{
    public static final PCLCardData DATA = register(Geomorphology.class, ConjurerResources.conjurer)
            .setPower(2, CardRarity.UNCOMMON)
            .setCostUpgrades(-1)
            .setAffinities(2, PCLAffinity.Orange)
            .setCore();

    public Geomorphology()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addGainPower(PTrigger.when(PCond.onSummon(), PMultiSkill.join(PMove.gainBlock(PCLCardTarget.Team,1), PMove.applyToEnemies(1, PCLElementHelper.Petra))));
    }
}

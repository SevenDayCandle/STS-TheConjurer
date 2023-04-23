package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.skills.skills.PTrigger;
import pinacolada.ui.combat.ConjurerReactionMeter;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class FutabaSakura extends PCLCard
{
    public static final PCLCardData DATA = register(FutabaSakura.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Immaterial, PCLCardTarget.RandomEnemy)
            .setDamage(2, 1)
            .setHp(4, 2)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public FutabaSakura()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.PSYCHOKINESIS);
        addGainPower(PTrigger.when(CCond.redox(), getSpecialMove(0, this::specialMove, 22)));
    }

    public void specialMove(PSpecialSkill move, PCLUseInfo info)
    {
        int am = GameUtilities.getRNG().random(move.amount);
        move.getActions().callback(() -> ConjurerReactionMeter.meter.addCount(am, true));
    }
}

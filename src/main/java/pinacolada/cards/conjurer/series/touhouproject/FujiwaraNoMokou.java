package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.base.moves.PMove_Reshuffle;

@VisibleCard
public class FujiwaraNoMokou extends PCLCard
{
    public static final PCLCardData DATA = register(FujiwaraNoMokou.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON)
            .setDamage(3, 1)
            .setPriority(1)
            .setHp(5, 1)
            .setAffinities(1, PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public FujiwaraNoMokou()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.BURN);
        addUseMove(PCond.onDeath(), PMultiSkill.join(new PMove_Reshuffle(), PMove.applyToTeam(3, PCLPowerHelper.Vigor)));
    }
}

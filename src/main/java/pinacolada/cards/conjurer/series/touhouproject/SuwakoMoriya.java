package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;

@VisibleCard
public class SuwakoMoriya extends PCLCard
{
    public static final PCLCardData DATA = register(SuwakoMoriya.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.COMMON, PCLAttackType.Normal)
            .setDamage(2, 0)
            .setHp(4, 2)
            .setAffinities(PCLAffinity.Orange, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public SuwakoMoriya()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.BITE);
        addUseMove(CCond.combust(), PMove.applyToSingle(3, PCLPowerHelper.Poison).setUpgrade(1));
    }
}

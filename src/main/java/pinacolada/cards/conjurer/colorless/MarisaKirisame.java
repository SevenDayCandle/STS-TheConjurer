package pinacolada.cards.conjurer.colorless;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class MarisaKirisame extends PCLCard
{
    public static final PCLCardData DATA = register(MarisaKirisame.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON)
            .setDamage(3, 1)
            .setPriority(1)
            .setHp(5, 1)
            .setAffinities(1, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.touhouProject, true);

    public MarisaKirisame()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.ELECTRIC);
        addUseMove(PCond.cooldown(4).setUpgrade(-1), PMove.apply(PCLCardTarget.None, 1, PCLPowerHelper.Critical));
    }
}

package pinacolada.cards.conjurer.series.genshinimpact;


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

@VisibleCard
public class Razor extends PCLCard
{
    public static final PCLCardData DATA = register(Razor.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.COMMON)
            .setDamage(4, 1)
            .setPriority(1)
            .setHp(7, 0)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Razor()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.BLUNT_LIGHT);
        addUseMove(PCond.cooldown(1), PMove.applyToSingle(1, PCLPowerHelper.Vulnerable).setUpgrade(1));
    }
}

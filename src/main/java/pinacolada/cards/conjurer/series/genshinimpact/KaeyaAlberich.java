package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class KaeyaAlberich extends PCLCard
{
    public static final PCLCardData DATA = register(KaeyaAlberich.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.COMMON)
            .setDamage(4, 0)
            .setPriority(1)
            .setHp(6, 3)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public KaeyaAlberich()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.ICE);
        addUseMove(PCond.cooldown(0), PMove.applyToSingle(2, PCLElementHelper.Gelus).setUpgrade(1));
    }
}

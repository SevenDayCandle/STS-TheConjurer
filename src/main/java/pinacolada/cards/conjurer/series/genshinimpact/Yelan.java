package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrait;

@VisibleCard
public class Yelan extends PCLCard
{
    public static final PCLCardData DATA = register(Yelan.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Ranged, PCLCardTarget.RandomEnemy)
            .setDamage(2, 0, 2)
            .setPriority(1)
            .setHp(5, 1)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Yelan()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.SLASH_HORIZONTAL).setChain(PMod.perLevel(1, PCLAffinity.Blue).setUpgrade(1), PTrait.damage(1));
        addUseMove(PCond.onSummon(), PMove.draw(1));
    }
}

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
import pinacolada.skills.PMove;

@VisibleCard
public class BarbaraPegg extends PCLCard
{
    public static final PCLCardData DATA = register(BarbaraPegg.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Magical, PCLCardTarget.AllEnemy)
            .setDamage(1, 0)
            .setPriority(1)
            .setHp(3, 2)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public BarbaraPegg()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.WATER);
        addUseMove(PCond.cooldown(0), PMove.heal(PCLCardTarget.AllAlly, 2).setUpgrade(1));
    }
}

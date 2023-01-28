package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PTrait;

@VisibleCard
public class IttoArataki extends PCLCard
{
    public static final PCLCardData DATA = register(IttoArataki.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.UNCOMMON)
            .setDamage(4, 1)
            .setPriority(1)
            .setHp(13, 2)
            .setAffinities(1, PCLAffinity.Red, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public IttoArataki()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.BLUNT_HEAVY).setChain(PCond.block(PCLCardTarget.Single, 1), PMod.perLevel(1, PCLAffinity.Orange), PTrait.hasDamage(2));
    }
}

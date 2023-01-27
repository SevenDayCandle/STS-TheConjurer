package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class Keqing extends PCLCard
{
    public static final PCLCardData DATA = register(Keqing.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Piercing, PCLCardTarget.RandomEnemy)
            .setDamage(2, 1, 2)
            .setPriority(1)
            .setHp(6, 1)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Keqing()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.DAGGER);
        addUseMove(PCond.onWithdraw(),
                PMod.reshufflePer(2, PCLCardGroupHelper.DiscardPile).edit(f -> f.setAffinity(PCLAffinity.Green)),
                PMove.applyToRandom(2, PCLElementHelper.Aer));
    }
}

package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class Venti extends PCLCard
{
    public static final PCLCardData DATA = register(Venti.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Ranged, PCLCardTarget.AllEnemy)
            .setDamage(2, 0, 2)
            .setRTags(PCLCardTag.Ethereal)
            .setPriority(1)
            .setHp(5, 2)
            .setAffinities(PCLAffinity.Green.make(2), PCLAffinity.Yellow.make(1))
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Venti()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.WIND);
        addUseMove(PCond.cooldown(1), PMod.cyclePer(2), PMove.applyToEnemies(2, PCLElementHelper.Aer));
    }
}

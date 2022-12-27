package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLAttackType;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

public class Ningguang extends PCLCard
{
    public static final PCLCardData DATA = register(Ningguang.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Ranged)
            .setDamage(3, 1)
            .setPriority(1)
            .setHp(9, 0)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Ningguang()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.EARTH);
        addUseMove(PCond.cooldown(2), PMultiSkill.choose(PMove.retain(1).setAffinity(PCLAffinity.Orange).setUpgrade(1), PMove.applyToEnemies(3, PCLElementHelper.Stoned).setUpgrade(2)));
    }
}

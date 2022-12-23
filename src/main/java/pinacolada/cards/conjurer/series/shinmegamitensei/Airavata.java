package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

public class Airavata extends PCLCard
{
    public static final PCLCardData DATA = register(Airavata.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.UNCOMMON)
            .setDamage(3, 0)
            .setPriority(1)
            .setHp(18, 2)
            .setAffinities(PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Airavata()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.BLUNT_HEAVY);
        addUseMove(PCond.cooldown(3).setUpgrade(-1), PMod.bonusPerLevel(4, PCLAffinity.Orange), PMove.gainBlock(6));
    }
}

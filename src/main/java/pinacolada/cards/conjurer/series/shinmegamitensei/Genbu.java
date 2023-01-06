package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLAttackType;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

public class Genbu extends PCLCard
{
    public static final PCLCardData DATA = register(Genbu.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.UNCOMMON, PCLAttackType.Magical)
            .setDamage(1, 1, 2)
            .setPriority(1)
            .setHp(8, 2)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Genbu()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.CLAW);
        addUseMove(PCond.cooldown(1),
                PMod.highestAffinityBranch(PCLAffinity.Blue, PCLAffinity.Green),
                PMultiSkill.join(
                        CMove.gainReaction(12).setUpgrade(3),
                        PMove.draw(1)
                )
        );
    }
}

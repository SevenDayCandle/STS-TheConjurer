package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.cards.base.*;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrigger;

public class Quetzalcoatl extends PCLCard
{
    public static final PCLCardData DATA = register(Quetzalcoatl.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.RARE, PCLAttackType.Magical)
            .setDamage(4, 0)
            .setPriority(1)
            .setHp(12, 4)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Quetzalcoatl()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.BITE);
        addGainPower(PTrigger.interactable(
                CCond.payReaction(10).setUpgrade(-1),
                PMove.fetch(1, PCLCardGroupHelper.DiscardPile).edit(f -> f.setAffinity(PCLAffinity.Red, PCLAffinity.Green).setRandom()))
        );
    }
}

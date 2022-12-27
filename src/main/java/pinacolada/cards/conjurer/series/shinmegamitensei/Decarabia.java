package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrigger;

public class Decarabia extends PCLCard
{
    public static final PCLCardData DATA = register(Decarabia.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON)
            .setDamage(2, 0)
            .setPriority(1)
            .setHp(5, 2)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Decarabia()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.FIRE);
        addGainPower(PTrigger.interactable(
                CCond.payReaction(20).setUpgrade(-5),
                PMove.modifyAffinity(1, 1, PCLAffinity.Red, PCLAffinity.Blue).setAlt(true).setCardGroup(PCLCardGroupHelper.Hand)
        ));
    }
}

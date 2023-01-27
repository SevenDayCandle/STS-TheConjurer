package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrigger;

@VisibleCard
public class Amon extends PCLCard
{
    public static final PCLCardData DATA = register(Amon.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.UNCOMMON, PCLAttackType.Piercing)
            .setDamage(4, 1)
            .setPriority(1)
            .setHp(9, 1)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Amon()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.BLUNT_HEAVY);
        addGainPower(PTrigger.interactable(
                CCond.payReaction(9),
                PMod.bonusPerLevel(5, PCLAffinity.Red).setUpgrade(1), PMove.dealDamage(5, AttackEffects.CLAW).setUpgrade(1)
        ));
    }
}

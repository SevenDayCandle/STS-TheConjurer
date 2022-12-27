package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.cards.base.*;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrigger;

public class Dadga extends PCLCard
{
    public static final PCLCardData DATA = register(Dadga.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.UNCOMMON, PCLAttackType.Normal, PCLCardTarget.AllEnemy)
            .setDamage(2, 1)
            .setPriority(1)
            .setHp(14, 2)
            .setAffinities(2, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Dadga()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.BLUNT_HEAVY);
        addGainPower(PTrigger.interactable(
                CCond.payReaction(10),
                PMove.stabilize(PCLCardTarget.AllEnemy, PCLElementHelper.Stoned))
        );
    }
}

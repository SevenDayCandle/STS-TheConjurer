package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrigger;

public class Cerberus extends PCLCard
{
    public static final PCLCardData DATA = register(Cerberus.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.UNCOMMON)
            .setDamage(6, 1)
            .setPriority(1)
            .setHp(10, 1)
            .setAffinities(2, PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Cerberus()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.BITE);
        addGainPower(PTrigger.interactable(
                CCond.payReaction(10),
                PMod.perCreatureWith(1, PCLElementHelper.Ignis), PMove.modifyDamage(2).setUpgrade(1)
        ));
    }
}

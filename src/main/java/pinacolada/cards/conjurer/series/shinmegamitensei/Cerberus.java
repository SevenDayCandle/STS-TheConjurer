package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Cerberus extends PCLCard
{
    public static final PCLCardData DATA = register(Cerberus.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.UNCOMMON)
            .setDamage(7, 1)
            .setHp(13, 1)
            .setAffinities(2, PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Cerberus()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.BITE);
        addGainPower(PTrigger.interactable(
                CCond.payReaction(9),
                PMod.perCreatureWith(1, PCLElementHelper.Ignis), PMove.modifyDamage(2).setUpgrade(1)
        ));
    }
}

package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class KawashiroNitori extends PCLCard
{
    public static final PCLCardData DATA = register(KawashiroNitori.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Ranged)
            .setDamage(2, 1)
            .setPriority(1)
            .setHp(5, 1)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public KawashiroNitori()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.WATER);
        addGainPower(PTrigger.interactable(
                CCond.payReaction(13),
                PMod.bonusPerLevel(2, PCLAffinity.Blue), PMove.gainBlock(PCLCardTarget.None, 6).setUpgrade(1)
        ));
    }
}
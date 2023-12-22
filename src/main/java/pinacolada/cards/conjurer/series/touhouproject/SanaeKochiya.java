package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.pcl.colorless.Miracle;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.FlowPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class SanaeKochiya extends PCLCard {
    public static final PCLCardData DATA = register(SanaeKochiya.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Immaterial)
            .setDamage(3, 0)
            .setHp(4, 2)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public SanaeKochiya() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.WIND);
        addGainPower(PTrigger.interactable(PCond.payPower(8, FlowPower.DATA), PMove.create(Miracle.DATA.ID)));
    }
}

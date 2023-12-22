package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.pcl.colorless.Insight;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class RinnosukeMorichika extends PCLCard {
    public static final PCLCardData DATA = register(RinnosukeMorichika.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Immaterial)
            .setDamage(2, 1)
            .setHp(6, 1)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public RinnosukeMorichika() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.BLUNT_LIGHT);
        addGainPower(PTrigger.interactable(
                CCond.payMatter(15),
                PMove.create(Insight.DATA.ID)
        ));
    }
}

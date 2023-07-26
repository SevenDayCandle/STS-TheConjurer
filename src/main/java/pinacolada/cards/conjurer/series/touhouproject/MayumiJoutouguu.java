package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;

@VisibleCard
public class MayumiJoutouguu extends PCLCard {
    public static final PCLCardData DATA = register(MayumiJoutouguu.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.COMMON, PCLAttackType.Normal)
            .setDamage(3, 0)
            .setHp(5, 2)
            .setAffinities(PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public MayumiJoutouguu() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.BLUNT_LIGHT);
        addUseMove(CCond.react(), PMove.gainBlockPlayer(3).setUpgrade(1));
    }
}

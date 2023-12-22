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
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class SakuyaIzayoi extends PCLCard {
    public static final PCLCardData DATA = register(SakuyaIzayoi.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Piercing)
            .setDamage(2, 0, 2)
            .setHp(5, 2)
            .setAffinities(PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public SakuyaIzayoi() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.DAGGER);
        addUseMove(CCond.react(), PCond.cycleRandom(1), PMove.dealDamageToRandom(6));
    }
}

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
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.modifiers.PMod_PerPotion;

@VisibleCard
public class EirinYagokoro extends PCLCard {
    public static final PCLCardData DATA = register(EirinYagokoro.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Immaterial)
            .setDamage(2, 0)
            .setHp(4, 1)
            .setAffinities(1, PCLAffinity.Blue, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.touhouProject, true);

    public EirinYagokoro() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.POISON).setBonus(new PMod_PerPotion(), 2, 1);
        addUseMove(PCond.onSummon(), PMove.gainTempHP(PCLCardTarget.None, 2).setUpgrade(1));
    }
}

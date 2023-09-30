package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class MarisaKirisame extends PCLCard {
    public static final PCLCardData DATA = register(MarisaKirisame.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Immaterial)
            .setDamage(2, 1)
            .setHp(5, 1)
            .setAffinities(1, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public MarisaKirisame() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.ELECTRIC);
        addUseMove(PCond.cooldown(3).setUpgrade(-1), PMove.gainPlayer(1, PCLPowerData.Critical));
    }
}

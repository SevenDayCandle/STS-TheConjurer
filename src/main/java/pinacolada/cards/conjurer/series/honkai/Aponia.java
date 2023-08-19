package pinacolada.cards.conjurer.series.honkai;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PTrait;

@VisibleCard
public class Aponia extends PCLCard {
    public static final PCLCardData DATA = register(Aponia.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Immaterial)
            .setDamage(1, 2)
            .setHp(3, 0)
            .setAffinities(2, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.honkai, true);

    public Aponia() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SMALL_LASER);
        addUseMove(CCond.linkFront(), PTrait.takeDamageMultiplier(-75));
    }
}

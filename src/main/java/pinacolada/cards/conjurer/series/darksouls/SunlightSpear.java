package pinacolada.cards.conjurer.series.darksouls;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.EffekseerEFK;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.conjurer.conditions.PCond_React;

@VisibleCard
public class SunlightSpear extends PCLCard {
    public static final PCLCardData DATA = register(SunlightSpear.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.RARE, PCLAttackType.Immaterial)
            .setDamage(9, 2)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public SunlightSpear() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.LIGHT03);
        addUseMove(new PCond_React(), PMove.modifyDamage(8).setUpgrade(3));
    }
}

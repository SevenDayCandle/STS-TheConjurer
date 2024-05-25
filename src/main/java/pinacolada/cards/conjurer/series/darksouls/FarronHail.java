package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.EffekseerEFK;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.conjurer.modifiers.PMod_PerLevel;

@VisibleCard
public class FarronHail extends PCLCard {
    public static final PCLCardData DATA = register(FarronHail.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.COMMON, PCLAttackType.Ranged, PCLCardTarget.AllEnemy)
            .setDamage(9, 3)
            .setAffinities(1, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public FarronHail() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.BLOW07).setBonus(new PMod_PerLevel(1, PCLAffinity.Blue), 2, 1);
    }
}

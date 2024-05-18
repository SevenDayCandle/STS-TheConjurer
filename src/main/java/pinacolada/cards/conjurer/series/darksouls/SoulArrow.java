package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.EffekseerEFK;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.conjurer.conditions.PCond_React;
import pinacolada.skills.conjurer.modifiers.PMod_PerLevel;

@VisibleCard
public class SoulArrow extends PCLCard {
    public static final PCLCardData DATA = register(SoulArrow.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON, PCLAttackType.Ranged)
            .setDamage(8, 3)
            .setAffinities(1, PCLAffinity.Blue, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public SoulArrow() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.BLOW07);
        addUseMove(PCond.exhaust(1), PMove.draw(3));
    }
}

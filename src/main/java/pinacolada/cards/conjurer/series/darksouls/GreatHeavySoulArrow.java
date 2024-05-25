package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.EffekseerEFK;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class GreatHeavySoulArrow extends PCLCard {
    public static final PCLCardData DATA = register(GreatHeavySoulArrow.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.UNCOMMON, PCLAttackType.Ranged)
            .setDamage(13, 4)
            .setAffinities(1, PCLAffinity.Blue, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public GreatHeavySoulArrow() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.BLOW07);
        addUseMove(PCond.exhaustRandom(2, PCLCardGroupHelper.DrawPile), PMove.gain(2, PCLPowerData.NextTurnDraw));
    }
}

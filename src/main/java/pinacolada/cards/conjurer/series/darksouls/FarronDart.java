package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.EffekseerEFK;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class FarronDart extends PCLCard {
    public static final PCLCardData DATA = register(FarronDart.class, ConjurerResources.conjurer)
            .setAttack(0, CardRarity.COMMON, PCLAttackType.Ranged)
            .setDamage(5, 2)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setTags(PCLCardTag.Exhaust)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public FarronDart() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.BLOW05);
        addUseMove(PMove.gain(2, PCLPowerData.NextTurnDraw).setUpgrade(1));
    }
}

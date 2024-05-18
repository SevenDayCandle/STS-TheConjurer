package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class CometAzur extends PCLCard {
    public static final PCLCardData DATA = register(CometAzur.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.RARE, PCLAttackType.Immaterial)
            .setTags(PCLCardTag.Exhaust)
            .setDamage(34, 10)
            .setAffinities(1, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public CometAzur() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(ConjurerEFK.MGC_W2_SuperSphereAttackImpact);
        addUseMove(PMove.gain(2, PCLPowerData.Energized, PCLPowerData.DrawMinus));
    }
}

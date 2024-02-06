package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.conjurer.modifiers.PMod_BonusOnReact;

@VisibleCard
public class BurnOFlame extends PCLCard {
    public static final PCLCardData DATA = register(BurnOFlame.class, ConjurerResources.conjurer)
            .setAttack(0, CardRarity.COMMON)
            .setDamage(5, 2)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public BurnOFlame() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(ConjurerEFK.FIRE07);
        addUseMove(new PMod_BonusOnReact(2), PMove.applyToSingle(2, BlastedPower.DATA));
    }
}

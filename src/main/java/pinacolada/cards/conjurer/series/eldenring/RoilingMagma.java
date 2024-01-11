package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.powers.conjurer.ForgingPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMod;
import pinacolada.skills.PMove;

@VisibleCard
public class RoilingMagma extends PCLCard {
    public static final PCLCardData DATA = register(RoilingMagma.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON)
            .setDamage(7, 2)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public RoilingMagma() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(ConjurerEFK.FIRE06);
        addUseMove(CMod.bonusOnReact(2), PMove.gain(2, ForgingPower.DATA));
    }
}

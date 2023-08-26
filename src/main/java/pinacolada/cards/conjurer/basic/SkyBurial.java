package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class SkyBurial extends PCLCard {
    public static final PCLCardData DATA = register(SkyBurial.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.RARE, PCLAttackType.Piercing, PCLCardTarget.AllEnemy)
            .setDamage(4, 1, 3)
            .setAffinities(1, PCLAffinity.Green)
            .setCore();

    public SkyBurial() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(ConjurerEFK.EVFXForge05_06_LiftingGust);
        addUseMove(PMod.discardPer(0).edit(f -> f.setForced(true)), PMove.gainTemporary(4, PCLPowerHelper.Thorns));
    }
}

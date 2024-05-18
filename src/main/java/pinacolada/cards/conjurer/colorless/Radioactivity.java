package pinacolada.cards.conjurer.colorless;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Radioactivity extends PCLCard {
    public static final PCLCardData DATA = register(Radioactivity.class, ConjurerResources.conjurer)
            .setPower(2, CardRarity.SPECIAL)
            .setCostUpgrades(-1)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Silver)
            .setCore(true);

    public Radioactivity() {
        super(DATA);
    }

    public void setup(Object input) {
        addGainPower(PTrigger.when(PCond.checkPower(PCLCardTarget.Any, 1).edit(f -> f.setDebuff(true)), PMod.perParentAmount(), PMove.loseHp(PCLCardTarget.AllEnemy, 1, PCLAttackVFX.WAVE.key)));
    }
}

package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.ConjurerEFK;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

@VisibleCard
public class Melt extends PCLCard {
    public static final PCLCardData DATA = register(Melt.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.RARE, PCLAttackType.Immaterial)
            .setDamage(10, 4)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue)
            .setMaxCopies(2)
            .setCore();

    public Melt() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(ConjurerEFK.MGC_PowerRelease);
        addUseMove(CCond.react(), PMultiSkill.join(PMove.selfExhaust(), PMove.loseHpPercent(PCLCardTarget.Single, 20)));
    }
}

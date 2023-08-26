package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class AuralDecoy extends PCLCard {
    public static final PCLCardData DATA = register(AuralDecoy.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE, PCLCardTarget.Single)
            .setBlock(9, 3)
            .setAffinities(PCLAffinity.Green)
            .setTags(PCLCardTag.Exhaust)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public AuralDecoy() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
        addApplyPower(PCLCardTarget.Single, 2, PTrigger.when(PCond.haveTakenDamage().setTarget(PCLCardTarget.None), PMod.perParentAmount(), PMove.dealDamage(1, PCLAttackVFX.WAVE.key, PCLCardTarget.Self)));
    }
}

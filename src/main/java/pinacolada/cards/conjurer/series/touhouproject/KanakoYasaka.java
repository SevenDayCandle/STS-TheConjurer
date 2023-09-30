package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.VentusPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class KanakoYasaka extends PCLCard {
    public static final PCLCardData DATA = register(KanakoYasaka.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.RARE, PCLAttackType.Piercing, PCLCardTarget.AllEnemy)
            .setDamage(3, 1)
            .setHp(7, 2)
            .setAffinities(1, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public KanakoYasaka() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.IRON);
        addGainPower(PTrigger.when(CCond.react(), PMove.applyToRandom(2, VentusPower.DATA)));
    }
}

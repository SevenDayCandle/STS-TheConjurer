package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.FlowPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class Tighnari extends PCLCard {
    public static final PCLCardData DATA = register(Tighnari.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.SPECIAL, PCLAttackType.Ranged)
            .setDamage(3, 1)
            .setHp(5, 2)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Tighnari() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.BLUNT_LIGHT);
        addUseMove(PCond.cooldown(2), PMod.scoutPer(3), PMove.gain(2, FlowPower.DATA));
    }
}

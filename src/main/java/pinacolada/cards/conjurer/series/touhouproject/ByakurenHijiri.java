package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class ByakurenHijiri extends PCLCard {
    public static final PCLCardData DATA = register(ByakurenHijiri.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.RARE, PCLAttackType.Piercing, PCLCardTarget.AllEnemy)
            .setDamage(4, 0)
            .setHp(8, 2)
            .setAffinities(1, PCLAffinity.Blue, PCLAffinity.Orange, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public ByakurenHijiri() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.EARTH);
        addUseMove(PCond.cooldown(4), PCond.checkPower(PCLCardTarget.Self, 0, PCLPowerData.Resistance).edit(f -> f.setNot(true)), PMove.applyTemporaryToEveryone(6, PCLPowerData.Resistance));
    }
}

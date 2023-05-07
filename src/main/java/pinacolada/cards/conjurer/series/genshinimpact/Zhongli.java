package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.conjurer.basic.ErodingTerra;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.DelayTiming;

@VisibleCard
public class Zhongli extends PCLCard {
    public static final PCLCardData DATA = register(Zhongli.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.RARE, PCLAttackType.Piercing, PCLCardTarget.Single, DelayTiming.EndOfTurnFirst)
            .setDamage(2, 0)
            .setHp(25, 3)
            .setAffinities(2, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Zhongli() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.EARTH);
        addUseMove(PCond.cooldown(6).setUpgrade(-1), PMove.playCopy(1, PCLCardTarget.None, ErodingTerra.DATA.ID));
    }
}

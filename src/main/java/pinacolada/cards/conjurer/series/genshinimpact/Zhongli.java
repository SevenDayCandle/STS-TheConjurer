package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.delay.DelayTiming;
import pinacolada.skills.skills.PMultiSkill;

@VisibleCard
public class Zhongli extends PCLCard {
    public static final PCLCardData DATA = register(Zhongli.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.RARE, PCLAttackType.Piercing, PCLCardTarget.AllEnemy, DelayTiming.EndOfTurnFirst)
            .setDamage(3, 1)
            .setHp(25, 4)
            .setAffinities(2, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Zhongli() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.EARTH);
        addUseMove(PCond.cooldown(2), PMultiSkill.choose(PMove.gainBlockPlayer(11), CMove.stabilize(PCLCardTarget.AllEnemy, PCLElementHelper.Petra)));
    }
}

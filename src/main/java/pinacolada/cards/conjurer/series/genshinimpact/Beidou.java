package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.DelayTiming;
import pinacolada.skills.skills.PMultiSkill;

@VisibleCard
public class Beidou extends PCLCard {
    public static final PCLCardData DATA = register(Beidou.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.COMMON, PCLAttackType.Normal, PCLCardTarget.Single, DelayTiming.EndOfTurnFirst)
            .setDamage(4, 1)
            .setHp(11, 2)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Beidou() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SLASH_HEAVY);
        addUseMove(PCond.cooldown(2), PMultiSkill.join(PMove.dealDamageToAll(3), PMove.gainBlock(PCLCardTarget.Team, 5)));
    }
}

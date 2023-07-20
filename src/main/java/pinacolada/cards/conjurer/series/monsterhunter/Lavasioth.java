package pinacolada.cards.conjurer.series.monsterhunter;


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
import pinacolada.skills.delay.DelayTiming;
import pinacolada.skills.skills.PMultiCond;

@VisibleCard
public class Lavasioth extends PCLCard {
    public static final PCLCardData DATA = register(Lavasioth.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.UNCOMMON, PCLAttackType.Normal, PCLCardTarget.Single, DelayTiming.EndOfTurnFirst)
            .setDamage(4, 2, 0)
            .setHp(8, 0)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public Lavasioth() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.FIRE);
        addUseMove(PMultiCond.or(PCond.onWithdraw(), PCond.onDeath()), PMove.retain(2), PMove.modifyDamage(2).useParent(true));
    }
}

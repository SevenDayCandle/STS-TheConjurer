package pinacolada.cards.conjurer.series.monsterhunter;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.skills.PTrait;
import pinacolada.skills.delay.DelayTiming;

@VisibleCard
public class Anjanath extends PCLCard {
    public static final PCLCardData DATA = register(Anjanath.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.UNCOMMON, PCLAttackType.Normal, PCLCardTarget.RandomEnemy, DelayTiming.EndOfTurnFirst)
            .setDamage(6, 1)
            .setHp(8, 0)
            .setAffinities(2, PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public Anjanath() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.BITE).setChain(PCond.havePlayed(PCLEnum.CardType.SUMMON), PTrait.damage(5).setUpgrade(2));
    }
}

package pinacolada.cards.conjurer.colorless;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrait;

@VisibleCard
public class Tigrex extends PCLCard {
    public static final PCLCardData DATA = register(Tigrex.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.UNCOMMON, PCLAttackType.Normal, PCLCardTarget.RandomEnemy)
            .setDamage(4, 1, 0)
            .setHp(15, 0)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Orange)
            .setColorless();

    public Tigrex() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.BITE).setChain(PMod.perCard(1, PCLCardGroupHelper.ExhaustPile), PTrait.damage(1));
        addUseMove(PCond.cooldown(0), PCond.exhaustRandom(1), PMove.gainTempHP(PCLCardTarget.Team, 2));
    }
}

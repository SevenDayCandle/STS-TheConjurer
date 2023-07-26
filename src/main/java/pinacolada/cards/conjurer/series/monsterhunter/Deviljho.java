package pinacolada.cards.conjurer.series.monsterhunter;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class Deviljho extends PCLCard {
    public static final PCLCardData DATA = register(Deviljho.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.UNCOMMON, PCLAttackType.Brutal, PCLCardTarget.RandomEnemy)
            .setDamage(4, 1, 0)
            .setHp(15, 0)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.monsterHunter, true);

    public Deviljho() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.BITE).setBonus(PMod.perCard(1, PCLCardGroupHelper.ExhaustPile), 1);
        addUseMove(PCond.cooldown(0), PCond.exhaustRandom(1), PMove.applyToEnemies(1, PCLPowerHelper.Vulnerable));
    }
}

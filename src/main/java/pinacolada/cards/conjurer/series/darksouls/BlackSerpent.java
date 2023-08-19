package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.EffekseerEFK;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class BlackSerpent extends PCLCard {
    public static final PCLCardData DATA = register(BlackSerpent.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.RARE, PCLAttackType.Immaterial, PCLCardTarget.RandomEnemy)
            .setDamage(7, 1)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public BlackSerpent() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.SWORD25).setBonus(PMod.perCreature(PCLCardTarget.AllAlly, 1), 7, 1);
        addUseMove(PMove.applyToEveryone(2, PCLPowerHelper.Weak));
    }
}

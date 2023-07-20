package pinacolada.cards.conjurer.series.monsterhunter;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Odogaron extends PCLCard {
    public static final PCLCardData DATA = register(Odogaron.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Piercing, PCLCardTarget.Single)
            .setDamage(5, 2, 0)
            .setHp(5, 1)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public Odogaron() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.CLAW);
        addUseMove(PCond.cooldown(2), PMove.applyToSingle(2, PCLPowerHelper.Bruised));
    }
}

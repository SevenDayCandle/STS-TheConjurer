package pinacolada.cards.conjurer.special;

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
import pinacolada.skills.skills.PMultiSkill;

@VisibleCard
public class Bite extends PCLCard {
    public static final PCLCardData DATA = register(Bite.class, ConjurerResources.conjurer)
            .setAttack(0, CardRarity.SPECIAL, PCLAttackType.Normal, PCLCardTarget.Single)
            .setDamage(5, 3)
            .setAffinities(PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.darkSouls, true);

    public Bite() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.BITE);
        addUseMove(PCond.fatal().setTarget(PCLCardTarget.Single).edit(f -> f.setRandom(true)), PMultiSkill.join(PMove.gainTempHP(9).setUpgrade(1)));
    }
}
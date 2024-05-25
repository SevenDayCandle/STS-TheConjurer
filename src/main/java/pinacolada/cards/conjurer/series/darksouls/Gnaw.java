package pinacolada.cards.conjurer.series.darksouls;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.VentusPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class Gnaw extends PCLCard {
    public static final PCLCardData DATA = register(Gnaw.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON, PCLAttackType.Normal, PCLCardTarget.Single)
            .setDamage(5, 3)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public Gnaw() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.BITE);
        addUseMove(PMod.perDistinctDebuff(PCLCardTarget.Single, 1).edit(f -> f.setNot(true)), PMove.applyToSingle(2, VentusPower.DATA));
    }
}
package pinacolada.cards.conjurer.series.darksouls;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.conjurer.modifiers.PMod_PerLevel;

@VisibleCard
public class DorhysGnawing extends PCLCard {
    public static final PCLCardData DATA = register(DorhysGnawing.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.UNCOMMON, PCLAttackType.Normal, PCLCardTarget.Single)
            .setDamage(9, 3)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public DorhysGnawing() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.BITE);
        addUseMove(new PMod_PerLevel(1, PCLAffinity.Green), PMove.applyToSingle(1, PCLPowerData.Poison));
    }
}
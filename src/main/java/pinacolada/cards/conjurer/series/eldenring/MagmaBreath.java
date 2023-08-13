package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.EffekseerEFK;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class MagmaBreath extends PCLCard {
    public static final PCLCardData DATA = register(MagmaBreath.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.UNCOMMON, PCLAttackType.Ranged)
            .setDamage(9, 2)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public MagmaBreath() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.FIRE02);
        addUseMove(PMod.bonusOnExhausted(4), PMove.applyToSingle(2, PCLElementHelper.Ignis).setUpgrade(1));
    }
}

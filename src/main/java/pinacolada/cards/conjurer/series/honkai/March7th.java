package pinacolada.cards.conjurer.series.honkai;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

@VisibleCard
public class March7th extends PCLCard {
    public static final PCLCardData DATA = register(March7th.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.COMMON, PCLAttackType.Ranged)
            .setDamage(3, 1)
            .setHp(4, 2)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.honkai);

    public March7th() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.ICE);
        addUseMove(CCond.link(PCLAffinity.Green, PCLAffinity.Blue), PMultiSkill.join(PMove.applyToSingle(2, AquaPower.DATA), PMove.applyToSingle(2, PCLPowerData.NextTurnBlock)));
    }
}

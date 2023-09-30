package pinacolada.cards.conjurer.colorless;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

@VisibleCard
public class Zagreus extends PCLCard {
    public static final PCLCardData DATA = register(Zagreus.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Normal)
            .setDamage(2, 1)
            .setHp(6, 2)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.hades, true);

    public Zagreus() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SLASH_DIAGONAL);
        addUseMove(PCond.onSummon(), PMultiSkill.choose(
                PMove.gain(4, PCLPowerData.Strength),
                PMove.gain(2, PCLPowerData.Ritual)
        ));
    }
}

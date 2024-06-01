package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.EffekseerEFK;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class BlackFireOrb extends PCLCard {
    public static final PCLCardData DATA = register(BlackFireOrb.class, ConjurerResources.conjurer)
            .setAttack(0, CardRarity.UNCOMMON, PCLAttackType.Immaterial)
            .setDamage(12, 3)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public BlackFireOrb() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.DARK04);
        addUseMove(PMove.gain(-2, PCLPowerData.Dexterity), PMove.gain(1, PCLPowerData.Focus));
    }
}

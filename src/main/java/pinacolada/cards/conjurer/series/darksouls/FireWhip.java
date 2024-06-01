package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.EffekseerEFK;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.conditions.PCond_HaveLastPlayed;

@VisibleCard
public class FireWhip extends PCLCard {
    public static final PCLCardData DATA = register(FireWhip.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON)
            .setDamage(7, 2)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public FireWhip() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.FIRE06).setBonus(PMod.perPowerSingle(AquaPower.DATA), 2, 1);
    }
}

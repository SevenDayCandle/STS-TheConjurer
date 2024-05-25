package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrait;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class SacredOath extends PCLCard {
    public static final PCLCardData DATA = register(SacredOath.class, ConjurerResources.conjurer)
            .setPower(1, CardRarity.UNCOMMON)
            .setAffinities(2, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public SacredOath() {
        super(DATA);
    }

    public void setup(Object input) {
        addGainPower(PTrigger.passive(PMultiSkill.join(PTrait.damageMultiplier(20).setUpgrade(10), PTrait.takeDamageMultiplier(-20).setUpgrade(10))));
    }
}

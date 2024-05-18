package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.monsters.PCLIntentType;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.powers.conjurer.ElementPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.modifiers.PMod_PerCreatureIntent;

@VisibleCard
public class GreatMagicShield extends PCLCard {
    public static final PCLCardData DATA = register(GreatMagicShield.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.UNCOMMON, PCLCardTarget.Self)
            .setBlock(11, 3)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public GreatMagicShield() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove().setBonus(PCond.checkPowerSelf(1, AquaPower.DATA, ElementPowerData.Weak).edit(f -> f.setRandom(true)), 9, 2);
    }
}

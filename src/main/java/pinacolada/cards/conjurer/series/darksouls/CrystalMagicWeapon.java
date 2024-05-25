package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.powers.conjurer.CooledPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class CrystalMagicWeapon extends PCLCard {
    public static final PCLCardData DATA = register(CrystalMagicWeapon.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Self)
            .setAffinities(2, PCLAffinity.Blue)
            .setTags(PCLCardTag.Exhaust)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public CrystalMagicWeapon() {
        super(DATA);
    }

    public void setup(Object input) {
        addGainPower(1, PTrigger.when(PCond.damage(PCLCardTarget.Self, 1), PMultiSkill.join(PMove.applyToSingle(5, AquaPower.DATA).setUpgrade(2))));
    }
}

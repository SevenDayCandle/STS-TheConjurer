package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.conjurer.PetraPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class HoarahLouxsEarthshaker extends PCLCard {
    public static final PCLCardData DATA = register(HoarahLouxsEarthshaker.class, ConjurerResources.conjurer)
            .setAttack(3, CardRarity.RARE, PCLAttackType.Normal, PCLCardTarget.AllEnemy)
            .setTags(PCLCardTag.Exhaust)
            .setDamage(23, 5)
            .setAffinities(2, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public HoarahLouxsEarthshaker() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove().setDamageEffect(ConjurerEFK.MGC_EarthSpell_LV3);
        addUseMove(PMod.payPerPower(PetraPower.DATA).setTarget(PCLCardTarget.All), PMove.gainBlock(3).setUpgrade(1));
    }
}

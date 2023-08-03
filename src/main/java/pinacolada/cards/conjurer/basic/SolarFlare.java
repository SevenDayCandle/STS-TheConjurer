package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.EffekseerEFK;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMod;
import pinacolada.skills.PMove;

@VisibleCard
public class SolarFlare extends PCLCard {
    public static final PCLCardData DATA = register(SolarFlare.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.RARE, PCLAttackType.Immaterial)
            .setDamage(8, 1)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Yellow)
            .setCore();

    public SolarFlare() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.SWORD24);
        addUseMove(CMod.perMatter(4).setExtra(15).setUpgrade(-1).setUpgradeExtra(5), PMove.modifyDamage(1));
    }
}

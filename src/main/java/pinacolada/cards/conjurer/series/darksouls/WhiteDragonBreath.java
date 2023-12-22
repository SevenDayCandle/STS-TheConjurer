package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.conjurer.CooledPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMod;
import pinacolada.skills.PMove;

@VisibleCard
public class WhiteDragonBreath extends PCLCard {
    public static final PCLCardData DATA = register(WhiteDragonBreath.class, ConjurerResources.conjurer)
            .setAttack(3, CardRarity.RARE, PCLAttackType.Immaterial)
            .setDamage(30, 5)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(1, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public WhiteDragonBreath() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(ConjurerEFK.MGC_W2_SuperSphereAttack).setBonus(CMod.perLevel(1, PCLAffinity.Blue), 8, 3);
        addUseMove(PMove.applyToSingle(12, CooledPower.DATA));
    }
}

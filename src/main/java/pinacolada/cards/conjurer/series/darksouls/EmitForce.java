package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.EffekseerEFK;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;

@VisibleCard
public class EmitForce extends PCLCard {
    public static final PCLCardData DATA = register(EmitForce.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON, PCLAttackType.Ranged)
            .setDamage(6, 3)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public EmitForce() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.SWORD09).setBonusPercent(PCond.pileHas(4, PCLCardGroupHelper.Hand), 100);
    }
}

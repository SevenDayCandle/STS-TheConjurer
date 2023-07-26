package pinacolada.cards.conjurer.colorless;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;

@VisibleCard
public class Zero extends PCLCard {
    public static final PCLCardData DATA = register(Zero.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Piercing, PCLCardTarget.Single)
            .setDamage(4, array(2, 0))
            .setHp(5, 1)
            .setAffinities(1, PCLAffinity.Red, PCLAffinity.Green, PCLAffinity.Silver)
            .setLoadout(ConjurerPlayerData.megaman, true);

    public Zero() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SLASH_HORIZONTAL).setBonus(PMod.perCard(PCLCardGroupHelper.Hand).edit(f -> f.setType(CardType.ATTACK)), 1, 0, 1);
    }
}

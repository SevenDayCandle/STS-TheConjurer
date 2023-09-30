package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.conjurer.special.Biohazard;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class Nilou extends PCLCard {
    public static final PCLCardData DATA = register(Nilou.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.SPECIAL, PCLAttackType.Piercing, PCLCardTarget.AllEnemy)
            .setDamage(2, 1)
            .setHp(5, 1)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Nilou() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.POISON).setBonus(PMod.perCard(1, PCLCardGroupHelper.ExhaustPile).edit(f -> f.setType(CardType.STATUS)), 3);
        addUseMove(PCond.cooldown(1), PMove.createDrawPile(2, Biohazard.DATA.ID));
    }
}

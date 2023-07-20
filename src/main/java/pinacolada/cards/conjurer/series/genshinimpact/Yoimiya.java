package pinacolada.cards.conjurer.series.genshinimpact;


import extendedui.utilities.CostFilter;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardSelection;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Yoimiya extends PCLCard {
    public static final PCLCardData DATA = register(Yoimiya.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Ranged, PCLCardTarget.RandomEnemy)
            .setDamage(1, array(0, 0), 4, array(1, 0))
            .setHp(4, array(1, 0))
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Yoimiya() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.BLUNT_LIGHT);
        addUseMove(PCond.cooldown(2).setUpgrade(0, -1), PMove.play(1, PCLCardTarget.RandomEnemy, PCLCardGroupHelper.DrawPile)
                .edit(f -> f.setType(CardType.ATTACK).setCost(CostFilter.Cost0, CostFilter.Cost1).setOrigin(PCLCardSelection.Random)));
    }
}

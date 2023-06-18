package pinacolada.cards.conjurer.series.touhouproject;


import extendedui.utilities.CostFilter;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Clownpiece extends PCLCard {
    public static final PCLCardData DATA = register(Clownpiece.class, ConjurerResources.conjurer)
            .setSummon(0, CardRarity.UNCOMMON, PCLAttackType.Normal, PCLCardTarget.RandomEnemy)
            .setDamage(1, 2)
            .setHp(3, 0)
            .setAffinities(PCLAffinity.Star)
            .setLoadout(ConjurerPlayerData.touhouProject, true);

    public Clownpiece() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.ELECTRIC);
        addUseMove(PCond.cooldown(1),
                PMove.play(1, PCLCardTarget.Single, PCLCardGroupHelper.Hand, PCLCardGroupHelper.DiscardPile, PCLCardGroupHelper.DrawPile).edit(f -> f.setCost(CostFilter.Cost0).setRandom())
                , PMove.applyToSingle(1, PCLPowerHelper.Provoked).useParent(true));
    }
}

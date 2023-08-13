package pinacolada.cards.conjurer.series.touhouproject;


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
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class LilyWhite extends PCLCard {
    public static final PCLCardData DATA = register(LilyWhite.class, ConjurerResources.conjurer)
            .setSummon(0, CardRarity.UNCOMMON, PCLAttackType.Ranged, PCLCardTarget.RandomEnemy)
            .setDamage(3, 1)
            .setHp(2, 1)
            .setAffinities(PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public LilyWhite() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.WIND);
        addUseMove(PCond.onWithdraw(), PMod.reshufflePer(0, PCLCardGroupHelper.DiscardPile).edit(f -> f.setAffinity(PCLAffinity.Green)), PMove.gainBlock(2));
    }
}

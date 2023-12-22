package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class AyaShameimaru extends PCLCard {
    public static final PCLCardData DATA = register(AyaShameimaru.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Piercing, PCLCardTarget.RandomEnemy)
            .setDamage(2, 0, 2)
            .setHp(5, 0)
            .setRTags(PCLCardTag.Ethereal)
            .setAffinities(2, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public AyaShameimaru() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.WIND);
        addUseMove(CCond.react(), PCond.discardRandom(1), PMove.gainPlayer(3, PCLPowerData.NextTurnDraw));
    }
}

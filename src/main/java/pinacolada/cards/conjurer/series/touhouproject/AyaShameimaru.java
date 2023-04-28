package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;

@VisibleCard
public class AyaShameimaru extends PCLCard {
    public static final PCLCardData DATA = register(AyaShameimaru.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Piercing)
            .setDamage(2, 1, 2)
            .setHp(4, 0)
            .setAffinities(2, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public AyaShameimaru() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SLASH_HORIZONTAL);
        addUseMove(CCond.redox(), PMove.fetchRandom(1, PCLCardGroupHelper.DrawPile).edit(f -> f.setType(CardType.SKILL)));
    }
}

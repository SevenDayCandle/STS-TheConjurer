package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Morgana extends PCLCard {
    public static final PCLCardData DATA = register(Morgana.class, ConjurerResources.conjurer)
            .setSummon(0, CardRarity.COMMON)
            .setDamage(2, 1)
            .setHp(4, 1)
            .setAffinities(PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Morgana() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.BLUNT_LIGHT);
        addUseMove(PCond.cooldown(1),
                PMove.scout(1).edit(f -> f.setType(PCLEnum.CardType.SUMMON)));
    }
}

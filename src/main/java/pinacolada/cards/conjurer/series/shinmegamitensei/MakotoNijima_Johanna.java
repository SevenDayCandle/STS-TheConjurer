package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.special.moves.PMove_ExhaustAlly;

@VisibleCard
public class MakotoNijima_Johanna extends PCLCard {
    public static final PCLCardData DATA = register(MakotoNijima_Johanna.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.SPECIAL, PCLAttackType.Ranged, PCLCardTarget.AllEnemy)
            .setDamage(7, 1)
            .setHp(8, 1)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public MakotoNijima_Johanna() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.BLUNT_LIGHT);
        addUseMove(PMove.draw(3).setUpgrade(1).edit(f -> f.setAffinity(PCLAffinity.Orange)), new PMove_ExhaustAlly(PCLCardTarget.Self, 1));
    }
}

package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class Warmth extends PCLCard {
    public static final PCLCardData DATA = register(Warmth.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON, PCLCardTarget.None)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public Warmth() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.retain(2), PMove.modifyDamage(5).edit(f -> f.setOr(true).setForced(true)).setUpgrade(2).useParent(true));
    }
}

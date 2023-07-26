package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.special.moves.PMove_RestoreCardHP;

@VisibleCard
public class Homeward extends PCLCard {
    public static final PCLCardData DATA = register(Homeward.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON, PCLCardTarget.SingleAlly)
            .setCostUpgrades(-1)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public Homeward() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.withdrawAlly(2), PMultiSkill.join(new PMove_RestoreCardHP(7).useParentForce(), PMove.modifyCostExact(0).useParentForce().edit(f -> f.setOr(true))));
    }
}

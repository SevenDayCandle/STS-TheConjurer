package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

@VisibleCard
public class NaturalSelection extends PCLCard {
    public static final PCLCardData DATA = register(NaturalSelection.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE, PCLCardTarget.Self)
            .setCostUpgrades(-1)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setCore();

    public NaturalSelection() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.fetch(1, PCLCardGroupHelper.DiscardPile)
                , PMultiSkill.join(
                        PMove.modifyAffinity(1, PCLAffinity.Green).useParent(true),
                        PMove.modifyTag(1, 1, PCLCardTag.Bounce).useParent(true)
                )
        );
    }
}

package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class SacredFlame extends PCLCard {
    public static final PCLCardData DATA = register(SacredFlame.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.RARE, PCLCardTarget.None)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public SacredFlame() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.modifyAffinity(2, 1, PCLAffinity.Red).edit(f -> f.setCardGroup(PCLCardGroupHelper.Hand)));
        addUseMove(PMove.takeDamage(5).setUpgrade(-3));
    }
}

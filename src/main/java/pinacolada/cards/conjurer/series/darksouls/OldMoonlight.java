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
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class OldMoonlight extends PCLCard {
    public static final PCLCardData DATA = register(OldMoonlight.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.RARE, PCLCardTarget.None)
            .setCostUpgrades(-1)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public OldMoonlight() {
        super(DATA);
    }

    public void setup(Object input) {
        addGainPower(PTrigger.whenPerCombat(2, PCond.onExhaust().edit(f -> f.setType(CardType.ATTACK)), PMove.createCopy(1).useParent(true), PMove.modifyAffinity(PCLAffinity.Blue).useParent(true)));
    }
}

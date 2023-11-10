package pinacolada.cards.conjurer.colorless;


import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardSelection;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.skills.skills.base.moves.PMove_Shuffle;

@VisibleCard
public class OracleSphere extends PCLCard {
    public static final PCLCardData DATA = register(OracleSphere.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.RARE, PCLCardTarget.None)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Blue)
            .setCore(true);

    public OracleSphere() {
        super(DATA);
    }

    public void action(PSpecialSkill move, PCLUseInfo info, PCLActions order) {
        PCLActions.last.reshuffleFromPile(name, move.amount, AbstractDungeon.player.drawPile).setDestination(PCLCardSelection.Top);
    }

    public void setup(Object input) {
        addUseMove(new PMove_Shuffle(), getSpecialMove(0, this::action, 5).setUpgrade(2));
    }
}

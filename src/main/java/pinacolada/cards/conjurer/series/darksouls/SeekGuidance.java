package pinacolada.cards.conjurer.series.darksouls;


import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardSelection;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.skills.skills.base.moves.PMove_Shuffle;

@VisibleCard
public class SeekGuidance extends PCLCard {
    public static final PCLCardData DATA = register(SeekGuidance.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.UNCOMMON, PCLCardTarget.None)
            .setAffinities(PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public SeekGuidance() {
        super(DATA);
    }

    public void action(PSpecialSkill move, PCLUseInfo info, PCLActions order) {
        PCLActions.last.reshuffleFromPile(name, move.refreshAmount(info), AbstractDungeon.player.drawPile).setDestination(PCLCardSelection.Top);
    }

    public void setup(Object input) {
        addUseMove(new PMove_Shuffle(), getSpecialMove(0, this::action, 3).setUpgrade(2));
    }
}

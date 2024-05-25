package pinacolada.cards.conjurer.series.darksouls;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardAffinities;
import pinacolada.cards.base.fields.PCLCardAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.powers.conjurer.CooledPower;
import pinacolada.powers.conjurer.FlowPower;
import pinacolada.powers.conjurer.ForgingPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.GameUtilities;

import java.util.ArrayList;

@VisibleCard
public class DeadAgain extends PCLCard {
    public static final PCLCardData DATA = register(DeadAgain.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE)
            .setTags(PCLCardTag.Ethereal.make(1, 0), PCLCardTag.Exhaust.make())
            .setAffinities(2, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public DeadAgain() {
        super(DATA);
    }

    public void action(PSpecialSkill move, PCLUseInfo info, PCLActions order) {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            PCLCardAffinities affs = GameUtilities.getPCLCardAffinities(c);
            if (affs.getLevel(PCLAffinity.General) >= 0) {
                for (PCLCardAffinity aff : affs.getCardAffinities(true)) {
                    switch (aff.type.ID) {
                        case PCLAffinity.ID_RED:
                            for (AbstractMonster mo : GameUtilities.getEnemies(true)) {
                                PCLActions.bottom.applyPower(info.source, mo, BlastedPower.DATA, aff.level * move.amount);
                            }
                            break;
                        case PCLAffinity.ID_BLUE:
                            for (AbstractMonster mo : GameUtilities.getEnemies(true)) {
                                PCLActions.bottom.applyPower(info.source, mo, CooledPower.DATA, aff.level * move.amount);
                            }
                            break;
                        case PCLAffinity.ID_GREEN:
                            PCLActions.bottom.applyPower(info.source, info.source, FlowPower.DATA, aff.level * move.amount);
                            break;
                        case PCLAffinity.ID_ORANGE:
                            PCLActions.bottom.applyPower(info.source, info.source, ForgingPower.DATA, aff.level * move.amount);
                            break;
                    }
                }
                order.modifyAffinityLevel(c, new ArrayList<>(), 0, false, true);
            }
        }
    }

    public void setup(Object input) {
        addSpecialMove(0, this::action, 2);
    }
}
package pinacolada.cards.conjurer.basic;


import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardAffinities;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.GameUtilities;

import java.util.List;

@VisibleCard
public class NaturalSelection extends PCLCard {
    public static final PCLCardData DATA = register(NaturalSelection.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.All)
            .setCostUpgrades(-1)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setCore();

    public NaturalSelection() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.reshuffle(1), getSpecialMove(0, this::specialMove, 1).setTarget(PCLCardTarget.All));
    }

    public void specialMove(PSpecialSkill move, PCLUseInfo info, PCLActions order) {
        List<? extends AbstractCard> cards = info.getDataAsList(AbstractCard.class);
        if (cards != null) {
            for (AbstractCard c : cards) {
                PCLCardAffinities cardAffinities = GameUtilities.getPCLCardAffinities(c);
                if (cardAffinities != null) {
                    for (PCLAffinity aff : cardAffinities.getAffinities(false, false)) {
                        PCLElementHelper debuff = PCLElementHelper.get(aff);
                        if (debuff != null) {
                            for (AbstractCreature target : move.target.getTargets(info.source, info.target)) {
                                PCLActions.delayed.stabilizePower(info.source, target, debuff, move.amount);
                            }
                        }
                    }
                }
            }
        }
    }
}

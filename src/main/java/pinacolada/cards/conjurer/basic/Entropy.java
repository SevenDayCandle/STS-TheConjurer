package pinacolada.cards.conjurer.basic;


import com.megacrit.cardcrawl.cards.AbstractCard;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardAffinities;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.effects.EffekseerEFK;
import pinacolada.effects.PCLEffects;
import pinacolada.powers.conjurer.ElementPowerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.GameUtilities;

import java.util.List;

@VisibleCard
public class Entropy extends PCLCard {
    public static final PCLCardData DATA = register(Entropy.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Yellow, PCLAffinity.Purple)
            .setCore();

    public Entropy() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.scry(3).setUpgrade(1, 0), getSpecialMove(0, this::specialMove, 1).setUpgrade(0, 1).setTarget(PCLCardTarget.Single));
    }

    public void specialMove(PSpecialSkill move, PCLUseInfo info, PCLActions order) {
        PCLEffects.Queue.playEFX(EffekseerEFK.HOZYO05);

        List<? extends AbstractCard> cards = info.getDataAsList(AbstractCard.class);
        if (cards != null) {
            for (AbstractCard c : cards) {
                PCLCardAffinities cardAffinities = GameUtilities.getPCLCardAffinities(c);
                if (cardAffinities != null) {
                    for (PCLAffinity aff : cardAffinities.getAffinities(true, true)) {
                        ElementPowerData debuff = ElementPowerData.get(aff);
                        if (debuff != null) {
                            order.applyPower(info.source, info.target, debuff, move.amount);
                        }
                    }
                }
            }
        }
    }
}

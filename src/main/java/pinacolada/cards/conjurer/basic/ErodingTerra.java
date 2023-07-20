package pinacolada.cards.conjurer.basic;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.powers.conjurer.PetraPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.utilities.GameUtilities;

import java.util.ArrayList;

@VisibleCard
public class ErodingTerra extends PCLCard {
    public static final PCLCardData DATA = register(ErodingTerra.class, ConjurerResources.conjurer)
            .setPower(3, CardRarity.RARE)
            .setAffinities(2, PCLAffinity.Orange)
            .setCostUpgrades(-1)
            .setMaxCopies(1)
            .setCore();

    public ErodingTerra() {
        super(DATA);
    }

    public void setup(Object input) {
        addSpecialPower(0, (s, i) -> new ErodingTerraPower(i.source, s), 50);
    }

    public static class ErodingTerraPower extends PSpecialCardPower {
        public ErodingTerraPower(AbstractCreature owner, PSkill<?> move) {
            super(ErodingTerra.DATA, owner, move);
        }

        @Override
        public void atEndOfRound() {
            super.atEndOfRound();
            ArrayList<PetraPower> powers = GameUtilities.getPowers(PetraPower.POWER_ID);
            for (PetraPower po : powers) {
                int stacks = MathUtils.ceil(po.amount * amount / 100f);
                if (po.stabilizeTurns > 0) {
                    PCLActions.bottom.gainBlock(stacks);
                }
                else {
                    PCLActions.last.applyPower(po.owner, PCLElementHelper.Petra, stacks);
                }
                flash();
            }
        }
    }
}

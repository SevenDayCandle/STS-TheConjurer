package pinacolada.relics.conjurer;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleRelic;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.interfaces.providers.PointerProvider;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.skills.skills.special.primary.PRoot;
import pinacolada.utilities.GameUtilities;

@VisibleRelic
public class WarpingCompoundEye extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(WarpingCompoundEye.class, ConjurerResources.conjurer)
            .setProps(RelicTier.SHOP, LandingSound.MAGICAL)
            .setLoadout(ConjurerPlayerData.honkai);

    public WarpingCompoundEye() {
        super(DATA);
    }

    public void setup() {
        addUseMove(new PRoot(), getSpecialMove(DATA.strings.DESCRIPTIONS[0], this::specialMove, 1, 1));
    }

    public void specialMove(PSpecialSkill move, PCLUseInfo info, PCLActions order) {
        order.callback(() -> {
            for (AbstractCard c : player.hand.group) {
                GameUtilities.modifyDamage(c, MathUtils.random(-move.amount, move.amount), false, false);
                GameUtilities.modifyBlock(c, MathUtils.random(-move.amount, move.amount), false, false);
                if (c instanceof PointerProvider) {
                    ((PointerProvider) c).doEffects(effect -> {
                        if (effect.getChild() == null) {
                            effect.setAmount(MathUtils.random(-move.amount, move.amount) + effect.baseAmount);
                        }
                    });
                }
            }
        });
    }
}
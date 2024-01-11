package pinacolada.relics.conjurer;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleRelic;
import pinacolada.cardmods.PermanentBlockModifier;
import pinacolada.cardmods.PermanentDamageModifier;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.interfaces.providers.PointerProvider;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.skills.skills.special.primary.PRoot;

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
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                int actualAmount = move.refreshAmount(info);
                PermanentDamageModifier.apply(c, MathUtils.random(-actualAmount, actualAmount));
                PermanentBlockModifier.apply(c, MathUtils.random(-actualAmount, actualAmount));
                if (c instanceof PointerProvider) {
                    ((PointerProvider) c).doEffects(effect -> {
                        if (effect.getChild() == null) {
                            effect.setAmount(MathUtils.random(-actualAmount, actualAmount) + effect.amount);
                        }
                    });
                }
            }
        });
    }
}
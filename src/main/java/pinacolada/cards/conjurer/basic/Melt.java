package pinacolada.cards.conjurer.basic;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import extendedui.utilities.EUIColors;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.dungeon.AffinityReactions;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.effects.ConjurerEFK;
import pinacolada.effects.EffekseerEFK;
import pinacolada.interfaces.subscribers.OnElementReactSubscriber;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;

@VisibleCard
public class Melt extends PCLCard {
    public static final PCLCardData DATA = register(Melt.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue)
            .setTags(PCLCardTag.Exhaust)
            .setMaxCopies(2)
            .setCore();

    public Melt() {
        super(DATA);
    }

    public void setup(Object input) {
        addSpecialPower(0, MeltPower::new, 10, 5).setUpgrade(10)
                .setTarget(PCLCardTarget.Single);
    }

    public static class MeltPower extends PSpecialCardPower implements OnElementReactSubscriber {
        public static final PCLPowerData PDATA = createFromCard(MeltPower.class, DATA);
        private int bonus;

        public MeltPower(AbstractCreature owner, AbstractCreature source, PSkill<?> move) {
            super(PDATA, owner, source, move);
            bonus = amount;
        }

        @Override
        public float atDamageReceive(float damage, DamageInfo.DamageType type) {
            return type == DamageInfo.DamageType.NORMAL ? damage * (1 + bonus / 100f) : damage;
        }

        @Override
        public void atEndOfRound() {
            super.atEndOfRound();
            bonus = amount;
        }

        @Override
        public float modifyOrbIncoming(float damage) {
            return damage * (1 + bonus / 100f);
        }

        @Override
        public void onElementReact(PCLUseInfo info, AffinityReactions reactions, AbstractCreature m) {
            if (m == owner) {
                bonus += move.extra;
                flash();
            }
        }

        @Override
        public void onInitialApplication() {
            super.onInitialApplication();
            PCLActions.bottom.playVFX(EffekseerEFK.efk(ConjurerEFK.MGC_PowerRelease));
            bonus = amount;
        }

        @Override
        public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, String.valueOf(bonus), x, y, fontScale, EUIColors.green(c.a));
            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, String.valueOf(move.extra), x, y + 15 * Settings.scale, fontScale, EUIColors.red(c.a));
        }
    }
}

package pinacolada.characters;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import extendedui.EUIUtils;
import extendedui.ui.EUIBase;
import pinacolada.cards.conjurer.basic.StrikeI;
import pinacolada.effects.PCLSFX;
import pinacolada.effects.vfx.FadingParticleEffect;
import pinacolada.effects.vfx.SmokeEffect;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.PCLRenderHelpers;

import java.util.ArrayList;
import java.util.List;

public class ConjurerCharacter extends PCLCharacter {
    public static final Color MAIN_COLOR = CardHelper.getColor(106, 210, 177);
    private ArrayList<AbstractGameEffect> deathEffects;

    public ConjurerCharacter() {
        super(ConjurerResources.conjurer.data, ConjurerResources.conjurer.images.createSpineAnimation(0.5f));
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return ConjurerResources.conjurer.cardColor;
    }

    @Override
    public Color getCardRenderColor() {
        return MAIN_COLOR.cpy();
    }

    @Override
    public Color getCardTrailColor() {
        return MAIN_COLOR.cpy();
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return PCLSFX.ORB_FROST_EVOKE;
    }

    @Override
    public List<CutscenePanel> getCutscenePanels() {
        return new ArrayList<>();
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    @Override
    public Color getSlashAttackColor() {
        return Color.CYAN;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]
                {
                        PCLEnum.AttackEffect.BURN,
                        PCLEnum.AttackEffect.ICE,
                        PCLEnum.AttackEffect.WIND,
                        PCLEnum.AttackEffect.EARTH,
                        };
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return StrikeI.DATA.create();
    }

    @Override
    public void playDeathAnimation() {
        super.playDeathAnimation();
        deathEffects = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            float rotation = MathUtils.random(0, 360f);
            deathEffects.add(FadingParticleEffect.obtain(EUIUtils.random(SmokeEffect.IMAGES).texture(), hb.cX, hb.cY)
                    .setColor(getCardRenderColor())
                    .setScaleTarget(0.2f + MathUtils.random(0, 0.1f), 0.75F + MathUtils.random(0, 0.15f), 5f)
                    .setRotation(rotation, MathUtils.random(-180.0F, 180.0F))
                    .setTargetPosition(hb.cX + EUIBase.scale(180) * MathUtils.cos(rotation), hb.cY + EUIBase.scale(180) * MathUtils.sin(rotation), MathUtils.random(30f, 80f))
                    .setDuration(2f, false)
            );
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        if (deathEffects != null) {
            for (AbstractGameEffect ef : deathEffects) {
                ef.update();
                ef.render(sb);
            }
            deathEffects.removeIf(e -> e.isDone);
        }
    }

    @Override
    protected void renderPlayerSkeleton() {
        this.skeleton.setColor(Color.SKY);
        PCLRenderHelpers.drawCRT(CardCrawlGame.psb, s -> sr.draw(CardCrawlGame.psb, this.skeleton));
    }

    @Override
    protected void renderPlayerSprite(SpriteBatch sb) {
        PCLRenderHelpers.drawCRT(sb,
                s -> this.animation.renderSprite(s, this.drawX + this.animX, this.drawY + this.animY + AbstractDungeon.sceneOffsetY));
    }
}
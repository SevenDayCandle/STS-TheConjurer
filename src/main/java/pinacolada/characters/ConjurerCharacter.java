package pinacolada.characters;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import extendedui.utilities.EUIColors;
import pinacolada.effects.PCLSFX;
import pinacolada.effects.vfx.SmokeParticleEffect;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.ui.PCLEnergyOrb;
import pinacolada.utilities.PCLRenderHelpers;

import java.util.ArrayList;
import java.util.List;

public class ConjurerCharacter extends PCLCharacter {
    public static final CharacterStrings characterStrings = ConjurerResources.conjurer.getCharacterStrings();
    public static final Color MAIN_COLOR = CardHelper.getColor(106, 210, 177);
    public static final String[] NAMES = characterStrings.NAMES;
    public static final String[] TEXT = characterStrings.TEXT;
    private ArrayList<AbstractGameEffect> deathEffects;

    public ConjurerCharacter() {
        super(NAMES[0], ConjurerResources.conjurer.playerClass, new PCLEnergyOrb(ConjurerResources.conjurer.images.getOrbTextures(), ConjurerResources.conjurer.images.orbFlash),
                ConjurerResources.conjurer.images.skeletonAtlas, ConjurerResources.conjurer.images.skeletonJson,
                ConjurerResources.conjurer.images.shoulder2, ConjurerResources.conjurer.images.shoulder1, ConjurerResources.conjurer.images.corpse, TEXT[0]);
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
    public AbstractCard getStartCardForEvent() {
        return new pinacolada.cards.conjurer.basic.Strike();
    }

    @Override
    public Color getCardTrailColor() {
        return MAIN_COLOR.cpy();
    }

    @Override
    public AbstractPlayer newInstance() {
        return new ConjurerCharacter();
    }

    @Override
    public void playDeathAnimation() {
        super.playDeathAnimation();
        deathEffects = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            deathEffects.add(new SmokeParticleEffect(hb.cX, hb.cY, 0, 2.2F, 180.0F, 0.75F, getCardRenderColor()));
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
        this.skeleton.setColor(EUIColors.white(0.7f));
        PCLRenderHelpers.drawGrayscale(CardCrawlGame.psb, s -> sr.draw(CardCrawlGame.psb, this.skeleton));
    }

    @Override
    protected void renderPlayerSprite(SpriteBatch sb) {
        PCLRenderHelpers.drawGrayscale(sb,
                s -> this.animation.renderSprite(s, this.drawX + this.animX, this.drawY + this.animY + AbstractDungeon.sceneOffsetY));
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
    public String getCustomModeCharacterButtonSoundKey() {
        return PCLSFX.ORB_FROST_EVOKE;
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
    public void reloadDefaultAnimation() {
        reloadAnimation(0.5f);
    }
}
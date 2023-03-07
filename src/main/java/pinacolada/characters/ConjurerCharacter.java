package pinacolada.characters;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import extendedui.ui.EUIBase;
import pinacolada.effects.SFX;
import pinacolada.effects.vfx.FadingParticleEffect;
import pinacolada.effects.vfx.ScreenGradientEffect;
import pinacolada.effects.vfx.SnowBurstEffect;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.ui.PCLEnergyOrb;
import pinacolada.utilities.PCLRenderHelpers;

import java.util.ArrayList;
import java.util.List;

public class ConjurerCharacter extends PCLCharacter
{
    public static final CharacterStrings characterStrings = ConjurerResources.conjurer.getCharacterStrings();
    public static final Color MAIN_COLOR = CardHelper.getColor(106, 210, 177);
    public static final String[] NAMES = characterStrings.NAMES;
    public static final String[] TEXT = characterStrings.TEXT;
    private int effectCount;

    public ConjurerCharacter()
    {
        super(NAMES[0], ConjurerResources.conjurer.playerClass, new PCLEnergyOrb(ConjurerResources.conjurer.images.getOrbTextures(), ConjurerResources.conjurer.images.orbFlash),
                ConjurerResources.conjurer.images.skeletonAtlas, ConjurerResources.conjurer.images.skeletonJson,
                ConjurerResources.conjurer.images.shoulder2, ConjurerResources.conjurer.images.shoulder1, ConjurerResources.conjurer.images.corpse, TEXT[0]);
    }

    @Override
    public AbstractCard.CardColor getCardColor()
    {
        return ConjurerResources.conjurer.cardColor;
    }

    @Override
    public Color getCardRenderColor()
    {
        return MAIN_COLOR.cpy();
    }

    @Override
    public AbstractCard getStartCardForEvent()
    {
        return new pinacolada.cards.conjurer.basic.Strike();
    }

    @Override
    public Color getCardTrailColor()
    {
        return MAIN_COLOR.cpy();
    }

    @Override
    public AbstractPlayer newInstance()
    {
        return new ConjurerCharacter();
    }

    @Override
    public BitmapFont getEnergyNumFont()
    {
        return FontHelper.energyNumFontBlue;
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey()
    {
        return SFX.ORB_FROST_EVOKE;
    }

    @Override
    public Color getSlashAttackColor()
    {
        return Color.CYAN;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect()
    {
        return new AbstractGameAction.AttackEffect[]
                {
                        PCLEnum.AttackEffect.BURN,
                        PCLEnum.AttackEffect.ICE,
                        PCLEnum.AttackEffect.WIND,
                        PCLEnum.AttackEffect.EARTH,
                };
    }

    @Override
    public List<CutscenePanel> getCutscenePanels() {
        effectCount = 0;
        return new ArrayList<>();
    }

    @Override
    public void updateVictoryVfx(ArrayList<AbstractGameEffect> effects) {
        if (effectCount == 0) {
            effects.add(new ScreenGradientEffect(1f, Color.BLACK, Color.TEAL, Color.BLACK, Color.TEAL, Color.BLACK, Color.LIME, Color.BLACK, Color.LIME).setLooping(true));
        }

        if (effectCount < 40)
        {
            int x = MathUtils.random(Settings.WIDTH);
            effects.add(new FadingParticleEffect(SnowBurstEffect.getRandomTexture(), MathUtils.random(Settings.WIDTH), - EUIBase.scale(40f))
                    .setBlendingMode(PCLRenderHelpers.BlendingMode.Glowing)
                    .setScale(MathUtils.random(0.4f, 1f))
                    .setRotation(0, MathUtils.random(150f, 360f))
                    .setTargetPosition(x, Settings.HEIGHT, MathUtils.random(1f, 50f))
                    .setDuration(MathUtils.random(1f, 2f), false)
            );
        }

        effectCount++;
    }
}
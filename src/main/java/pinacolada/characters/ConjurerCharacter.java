package pinacolada.characters;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import pinacolada.effects.SFX;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.ui.PCLEnergyOrb;

public class ConjurerCharacter extends PCLCharacter
{
    public static final CharacterStrings characterStrings = PGR.getCharacterStrings("Conjurer");
    public static final Color MAIN_COLOR = CardHelper.getColor(106, 210, 177);
    public static final String[] NAMES = characterStrings.NAMES;
    public static final String[] TEXT = characterStrings.TEXT;

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
        return SFX.TINGSHA;
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
                        AbstractGameAction.AttackEffect.SLASH_HEAVY,
                        AbstractGameAction.AttackEffect.FIRE,
                        AbstractGameAction.AttackEffect.SLASH_DIAGONAL,
                        AbstractGameAction.AttackEffect.SLASH_HEAVY,
                        AbstractGameAction.AttackEffect.FIRE,
                        AbstractGameAction.AttackEffect.SLASH_DIAGONAL
                };
    }
}
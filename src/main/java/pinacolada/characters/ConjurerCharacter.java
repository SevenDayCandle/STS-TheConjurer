package pinacolada.characters;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import extendedui.ui.TextureCache;
import pinacolada.effects.SFX;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.resources.pcl.PCLCoreResources;
import pinacolada.ui.PCLEnergyOrb;

public class ConjurerCharacter extends PCLCharacter
{
    public static final TextureCache[] ORB_TEXTURES =
            {
                    ConjurerResources.conjurer.images.ORB_BASE_LAYER,
                    ConjurerResources.conjurer.images.ORB_TOP_LAYER1,
                    ConjurerResources.conjurer.images.ORB_TOP_LAYER2,
                    ConjurerResources.conjurer.images.ORB_TOP_LAYER3,
                    ConjurerResources.conjurer.images.ORB_TOP_LAYER4,
                    };
    public static final CharacterStrings characterStrings = PCLCoreResources.getCharacterStrings("Conjurer");
    public static final Color MAIN_COLOR = CardHelper.getColor(106, 210, 177);
    public static final String[] NAMES = characterStrings.NAMES;
    public static final String[] TEXT = characterStrings.TEXT;

    public ConjurerCharacter()
    {
        super(NAMES[0], ConjurerResources.conjurer.playerClass, new PCLEnergyOrb(ORB_TEXTURES, ConjurerResources.conjurer.images.ORB_FLASH),
                ConjurerResources.conjurer.images.SKELETON_ATLAS, ConjurerResources.conjurer.images.SKELETON_JSON,
                ConjurerResources.conjurer.images.SHOULDER2_PNG, ConjurerResources.conjurer.images.SHOULDER1_PNG, ConjurerResources.conjurer.images.CORPSE_PNG, TEXT[0]);
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
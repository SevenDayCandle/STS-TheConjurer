package pinacolada.resources.conjurer;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import extendedui.EUIUtils;
import pinacolada.characters.ConjurerCharacter;
import pinacolada.misc.CombatManager;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.PCLResources;
import pinacolada.ui.characterSelection.PCLLoadoutsContainer;
import pinacolada.ui.combat.ConjurerReactionMeter;

public class ConjurerResources extends PCLResources<ConjurerConfig, ConjurerImages, ConjurerTooltips>
{
    public static final String ID = "conjurer";
    public static final ConjurerResources conjurer = new ConjurerResources();

    public ConjurerResources()
    {
        super(ID, PCLEnum.Cards.THE_CONJURER, PCLEnum.Characters.THE_CONJURER, new ConjurerPlayerData(), new ConjurerConfig(), new ConjurerImages(ID));
    }

    public void initializeColor()
    {
        Color color = CardHelper.getColor(106, 210, 177);
        BaseMod.addColor(cardColor, color, color, color, color, color, color, color,
                images.attack, images.skill, images.power,
                images.orbA, images.attackL, images.skillL,
                images.powerL, images.orbB, images.orbC);
    }

    protected void initializeCards()
    {
        EUIUtils.logInfo(this, "InitializeCards();");

        tooltips = new ConjurerTooltips();
        loadCustomCards();
        loadCustomRelics();
    }

    protected void initializeStrings()
    {
        EUIUtils.logInfo(this, "InitializeStrings();");

        loadCustomStrings(CharacterStrings.class);
        loadCustomCardStrings();
        loadCustomStrings(RelicStrings.class);
        loadCustomStrings(PowerStrings.class);
        loadCustomStrings(PotionStrings.class);
    }

    protected void initializePotions()
    {
        EUIUtils.logInfo(this, "InitializePotions();");

        loadCustomPotions();
    }

    protected void initializeCharacter()
    {
        BaseMod.addCharacter(new ConjurerCharacter(), images.charButton, images.charBackground, playerClass);
    }

    protected void postInitialize()
    {
        data.initialize();
        PCLLoadoutsContainer.preloadResources(data);
        CombatManager.playerSystem.registerMeter(playerClass, ConjurerReactionMeter.meter);
        tooltips.initializeIcons();
    }

}

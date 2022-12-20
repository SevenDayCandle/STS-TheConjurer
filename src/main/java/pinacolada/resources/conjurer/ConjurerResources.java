package pinacolada.resources.conjurer;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.helpers.CardHelper;
import pinacolada.characters.ConjurerCharacter;
import pinacolada.misc.CombatManager;
import pinacolada.resources.PCLResources;
import pinacolada.ui.combat.ConjurerReactionMeter;

public class ConjurerResources extends PCLResources<ConjurerConfig, ConjurerImages, ConjurerTooltips>
{
    public static final String ID = "conjurer";
    public static final ConjurerResources conjurer = new ConjurerResources();

    public ConjurerResources()
    {
        super(ID, ConjurerEnum.Cards.THE_CONJURER, ConjurerEnum.Characters.THE_CONJURER, new ConjurerConfig(), new ConjurerImages(ID), ConjurerPlayerData::new);
    }

    public void initializeColor()
    {
        Color color = CardHelper.getColor(106, 210, 177);
        BaseMod.addColor(cardColor, color, color, color, color, color, color, color,
                images.attack, images.skill, images.power,
                images.orbA, images.attackL, images.skillL,
                images.powerL, images.orbB, images.orbC);
    }

    protected void initializePotions()
    {
        loadCustomPotions();
    }

    public void receiveEditCards()
    {
        tooltips = new ConjurerTooltips();
        loadCustomCards();
    }

    public void receiveEditCharacters()
    {
        BaseMod.addCharacter(new ConjurerCharacter(), images.charButton, images.charBackground, playerClass);
    }

    protected void postInitialize()
    {
        super.postInitialize();
        CombatManager.playerSystem.registerMeter(playerClass, ConjurerReactionMeter.meter);
    }

}

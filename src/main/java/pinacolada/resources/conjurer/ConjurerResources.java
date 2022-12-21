package pinacolada.resources.conjurer;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.helpers.CardHelper;
import extendedui.EUIUtils;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCardAffinity;
import pinacolada.characters.ConjurerCharacter;
import pinacolada.misc.CombatManager;
import pinacolada.monsters.PCLCardAlly;
import pinacolada.monsters.animations.PCLAllyAnimation;
import pinacolada.monsters.animations.conjurer.*;
import pinacolada.monsters.animations.pcl.PCLGeneralAllyAnimation;
import pinacolada.resources.PCLResources;
import pinacolada.resources.PGR;
import pinacolada.ui.combat.ConjurerReactionMeter;

public class ConjurerResources extends PCLResources<ConjurerConfig, ConjurerImages, ConjurerTooltips>
{
    public static final String ID = "conjurer";
    public static final ConjurerResources conjurer = new ConjurerResources();
    public static final PCLAffinity[] affinities = EUIUtils.array(PCLAffinity.Red, PCLAffinity.Green, PCLAffinity.Blue, PCLAffinity.Orange);

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
        PCLAffinity.registerAvailableAffinities(cardColor, affinities);
        PCLAffinity.registerAffinityBorder(cardColor, PGR.core.images.core.borderSpecial2);
        PCLCardAlly.registerAnimation(cardColor, this::getAnimation);
    }

    protected PCLAllyAnimation getAnimation(PCLCardAlly ally)
    {
        PCLCardAffinity highest = ally.hasCard() ? ally.card.affinities.getHighest() : null;
        if (highest != null)
        {
            switch (highest.type)
            {
                case Star:
                    return new ConjurerStarAllyAnimation(ally);
                case Red:
                    return new ConjurerFireAllyAnimation(ally);
                case Green:
                    return new ConjurerAirAllyAnimation(ally);
                case Blue:
                    return new ConjurerWaterAllyAnimation(ally);
                case Orange:
                    return new ConjurerEarthAllyAnimation(ally);
            }
        }
        return new PCLGeneralAllyAnimation(ally);
    }

}

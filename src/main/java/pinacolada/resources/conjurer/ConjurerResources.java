package pinacolada.resources.conjurer;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.*;
import com.megacrit.cardcrawl.cards.curses.*;
import com.megacrit.cardcrawl.cards.status.*;
import com.megacrit.cardcrawl.cards.tempCards.Insight;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.helpers.CardHelper;
import extendedui.EUIUtils;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardAffinity;
import pinacolada.cards.conjurer.curse.Curse_Normality;
import pinacolada.cards.pcl.curse.*;
import pinacolada.cards.pcl.status.*;
import pinacolada.characters.ConjurerCharacter;
import pinacolada.dungeon.CombatManager;
import pinacolada.monsters.PCLCardAlly;
import pinacolada.monsters.animations.PCLAllyAnimation;
import pinacolada.monsters.animations.conjurer.*;
import pinacolada.monsters.animations.pcl.PCLGeneralAllyAnimation;
import pinacolada.resources.PCLResources;
import pinacolada.resources.pcl.PCLCoreImages;
import pinacolada.ui.combat.ConjurerReactionMeter;

import java.util.HashSet;

public class ConjurerResources extends PCLResources<ConjurerPlayerData, ConjurerImages, ConjurerTooltips, ConjurerStrings>
{
    public static final String ID = "conjurer";
    public static final ConjurerResources conjurer = new ConjurerResources();
    public static final PCLAffinity[] affinities = EUIUtils.array(PCLAffinity.Red, PCLAffinity.Green, PCLAffinity.Blue, PCLAffinity.Orange);

    public ConjurerResources()
    {
        super(ID, ConjurerEnum.Cards.THE_CONJURER, ConjurerEnum.Characters.THE_CONJURER, new ConjurerImages(ID));
    }

    @Override
    public void initializeColor()
    {
        Color color = CardHelper.getColor(106, 210, 177);
        BaseMod.addColor(cardColor, color, color, color, color, color, color, color,
                images.attack, images.skill, images.power,
                images.orbA, images.attackL, images.skillL,
                images.powerL, images.orbB, images.orbC);
    }

    @Override
    public ConjurerPlayerData getData()
    {
        return new ConjurerPlayerData(this);
    }

    @Override
    public ConjurerTooltips getTooltips()
    {
        return new ConjurerTooltips();
    }

    @Override
    public ConjurerStrings getStrings()
    {
        return new ConjurerStrings(this);
    }

    @Override
    public void receiveEditCharacters()
    {
        BaseMod.addCharacter(new ConjurerCharacter(), images.charButton, images.charBackground, playerClass);
    }

    protected void postInitialize()
    {
        super.postInitialize();
        CombatManager.playerSystem.registerMeter(playerClass, ConjurerReactionMeter.meter);
        PCLAffinity.registerAvailableAffinities(cardColor, affinities);
        PCLAffinity.registerAffinityBorder(cardColor, PCLCoreImages.Core.borderSpecial2);
        PCLCardAlly.registerAnimation(cardColor, this::getAnimation);
    }

    protected PCLAllyAnimation getAnimation(PCLCardAlly ally)
    {
        HashSet<PCLAffinity> available = new HashSet<>(PCLAffinity.getAvailableAffinitiesAsList());
        available.add(PCLAffinity.Star);

        PCLCardAffinity highest = ally.hasCard() ? ally.card.affinities.getHighest(cAff -> available.contains(cAff.type)) : null;
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

    @Override
    public boolean containsColorless(AbstractCard card)
    {
        return card instanceof PCLCard;
    }

    @Override
    public boolean filterColorless(AbstractCard card)
    {
        return card instanceof PCLCard && ((PCLCard) card).cardData.resources == this;
    }

    @Override
    public PCLCardData getReplacement(String cardID)
    {
        switch (cardID)
        {
            case Apparition.ID:
                return pinacolada.cards.conjurer.special.Apparition.DATA;
            case AscendersBane.ID:
                return Curse_AscendersBane.DATA;
            case Bite.ID:
                return pinacolada.cards.conjurer.special.Bite.DATA;
            case Burn.ID:
                return Status_Burn.DATA;
            case Clumsy.ID:
                return Curse_Clumsy.DATA;
            case Dazed.ID:
                return Status_Dazed.DATA;
            case Decay.ID:
                return Curse_Decay.DATA;
            case Doubt.ID:
                return Curse_Doubt.DATA;
            case Injury.ID:
                return Curse_Injury.DATA;
            case Insight.ID:
                return pinacolada.cards.pcl.colorless.Insight.DATA;
            case JAX.ID:
                return pinacolada.cards.conjurer.special.JAX.DATA;
            case Madness.ID:
                return pinacolada.cards.pcl.colorless.Madness.DATA;
            case Miracle.ID:
                return pinacolada.cards.pcl.colorless.Miracle.DATA;
            case Normality.ID:
                return Curse_Normality.DATA;
            case Pain.ID:
                return Curse_Pain.DATA;
            case Parasite.ID:
                return Curse_Parasite.DATA;
            case Regret.ID:
                return Curse_Regret.DATA;
            case RitualDagger.ID:
                return pinacolada.cards.conjurer.special.RitualDagger.DATA;
            case Shame.ID:
                return Curse_Shame.DATA;
            case Slimed.ID:
                return Status_Slimed.DATA;
            case VoidCard.ID:
                return Status_Void.DATA;
            case Wound.ID:
                return Status_Wound.DATA;
            case Writhe.ID:
                return Curse_Writhe.DATA;
            default:
                return null;
        }
    }

    @Override
    public PCLCardData getAscendersBane()
    {
        return Curse_AscendersBane.DATA;
    }

}

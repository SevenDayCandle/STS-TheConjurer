package pinacolada.resources.conjurer;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Apparition;
import com.megacrit.cardcrawl.cards.colorless.Bite;
import com.megacrit.cardcrawl.cards.colorless.JAX;
import com.megacrit.cardcrawl.cards.colorless.RitualDagger;
import com.megacrit.cardcrawl.helpers.CardHelper;
import extendedui.EUIUtils;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.TemplateCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardAffinity;
import pinacolada.cards.pcl.curse.Curse_AscendersBane;
import pinacolada.characters.ConjurerCharacter;
import pinacolada.dungeon.CombatManager;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.monsters.PCLCardAlly;
import pinacolada.monsters.PCLTutorialMonster;
import pinacolada.monsters.animations.PCLAllyAnimation;
import pinacolada.monsters.animations.conjurer.*;
import pinacolada.monsters.animations.pcl.PCLGeneralAllyAnimation;
import pinacolada.monsters.tutorials.ConjurerSummonTutorialMonster;
import pinacolada.monsters.tutorials.ConjurerTutorialMonster;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.PCLResources;
import pinacolada.resources.pcl.PCLCoreImages;

import java.util.HashSet;

public class ConjurerResources extends PCLResources<ConjurerPlayerData, ConjurerImages, ConjurerTooltips, ConjurerStrings> {
    public static final String ID = "conjurer";
    public static final ConjurerResources conjurer = new ConjurerResources();
    public static final PCLAffinity[] affinities = EUIUtils.array(PCLAffinity.Red, PCLAffinity.Green, PCLAffinity.Blue, PCLAffinity.Orange);

    public ConjurerResources() {
        super(ID, ConjurerEnum.Cards.THE_CONJURER, ConjurerEnum.Characters.THE_CONJURER, new ConjurerImages(ID));
    }

    @Override
    public boolean containsColorless(AbstractCard card) {
        return card instanceof PCLCard;
    }

    @Override
    public boolean filterColorless(AbstractCard card) {
        return card instanceof PCLCard && ((PCLCard) card).cardData.resources == this;
    }

    @Override
    public PCLCardData getAscendersBane() {
        return Curse_AscendersBane.DATA;
    }

    @Override
    public String getReplacement(String cardID) {
        switch (cardID) {
            case Apparition.ID:
                return pinacolada.cards.conjurer.special.Apparition.DATA.ID;
            case Bite.ID:
                return pinacolada.cards.conjurer.special.Bite.DATA.ID;
            case JAX.ID:
                return pinacolada.cards.conjurer.special.JAX.DATA.ID;
            case RitualDagger.ID:
                return pinacolada.cards.conjurer.special.RitualDagger.DATA.ID;
            default:
                TemplateCardData data = TemplateCardData.getTemplate(cardID);
                return data != null ? data.ID : null;
        }
    }

    @Override
    public void initializeColor() {
        Color color = CardHelper.getColor(106, 210, 177);
        BaseMod.addColor(cardColor, color, color, color, color, color, color, color,
                images.attack, images.skill, images.power,
                images.orbA, images.attackL, images.skillL,
                images.powerL, images.orbB, images.orbC);
    }

    protected void postInitialize() {
        super.postInitialize();
        CombatManager.playerSystem.registerMeter(playerClass, ConjurerReactionMeter.meter);
        PCLAffinity.registerAvailableAffinities(cardColor, affinities);
        PCLAffinity.registerAffinityBorder(cardColor, PCLCoreImages.Core.borderSpecial2);
        PCLCardAlly.registerAnimation(cardColor, this::getAnimation);
        data.addTutorial(ConjurerTutorialMonster.DATA);
        PCLTutorialMonster.register(ConjurerSummonTutorialMonster.DATA, data.config.seenSummonTutorial, p -> p.chosenClass == data.resources.playerClass && EUIUtils.any(p.masterDeck.group, card -> card.type == PCLEnum.CardType.SUMMON));
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new ConjurerCharacter(), images.charButton, "", playerClass); // No portrait
    }

    @Override
    public ConjurerPlayerData getData() {
        return new ConjurerPlayerData(this);
    }

    @Override
    public ConjurerStrings getStrings() {
        return new ConjurerStrings(this);
    }

    @Override
    public ConjurerTooltips getTooltips() {
        return new ConjurerTooltips();
    }

    protected PCLAllyAnimation getAnimation(PCLCardAlly ally) {
        HashSet<PCLAffinity> available = new HashSet<>(PCLAffinity.getAvailableAffinitiesAsList());
        available.add(PCLAffinity.Star);

        PCLCardAffinity highest = ally.hasCard() ? ally.card.affinities.getHighest(cAff -> available.contains(cAff.type)) : null;
        if (highest != null) {
            switch (highest.type) {
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

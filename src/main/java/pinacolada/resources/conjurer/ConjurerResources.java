package pinacolada.resources.conjurer;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Bite;
import com.megacrit.cardcrawl.cards.colorless.RitualDagger;
import com.megacrit.cardcrawl.helpers.CardHelper;
import extendedui.EUI;
import extendedui.EUIUtils;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.TemplateCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardAffinity;
import pinacolada.cards.conjurer.series.darksouls.DorhysGnawing;
import pinacolada.cards.conjurer.series.eldenring.BlackKnife;
import pinacolada.cards.pcl.curse.Curse_AscendersBane;
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
import pinacolada.resources.loadout.PCLLoadout;
import pinacolada.resources.pcl.PCLCoreImages;
import pinacolada.utilities.GameUtilities;

import java.util.HashSet;

public class ConjurerResources extends PCLResources<ConjurerPlayerData, ConjurerImages, ConjurerTooltips, ConjurerStrings> {
    public static final String ID = "conjurer";
    public static final ConjurerResources conjurer = new ConjurerResources();

    public ConjurerResources() {
        super(ID, ConjurerEnum.Cards.THE_CONJURER, ConjurerEnum.Characters.THE_CONJURER, new ConjurerImages(ID));
    }

    @Override
    public boolean containsColorless(AbstractCard card) {
        if (card instanceof PCLCard) {
            PCLLoadout loadout = ((PCLCard) card).cardData.loadout;
            return loadout == null || loadout.isCore() || !loadout.isLocked();
        }
        return false;
    }

    @Override
    public boolean filterColorless(AbstractCard card) {
        return (card instanceof PCLCard && ((PCLCard) card).cardData.resources == this) && !data.config.allowColorlessForAll.get();
    }

    protected PCLAllyAnimation getAnimation(PCLCardAlly ally) {
        HashSet<PCLAffinity> available = new HashSet<>(PCLAffinity.getAvailableAffinities());
        available.add(PCLAffinity.Star);

        PCLCardAffinity highest = ally.hasCard() ? ally.card.affinities.getHighest(cAff -> available.contains(cAff.type)) : null;
        if (highest != null) {
            switch (highest.type.ID) {
                case PCLAffinity.ID_STAR:
                    return new ConjurerStarAllyAnimation(ally);
                case PCLAffinity.ID_RED:
                    return new ConjurerFireAllyAnimation(ally);
                case PCLAffinity.ID_BLUE:
                    return new ConjurerWaterAllyAnimation(ally);
                case PCLAffinity.ID_GREEN:
                    return new ConjurerAirAllyAnimation(ally);
                case PCLAffinity.ID_ORANGE:
                    return new ConjurerEarthAllyAnimation(ally);
            }
        }
        return new PCLGeneralAllyAnimation(ally);
    }

    @Override
    public PCLCardData getAscendersBane() {
        return Curse_AscendersBane.DATA;
    }

    @Override
    public ConjurerPlayerData getData() {
        return new ConjurerPlayerData(this);
    }

    @Override
    public String getEventReplacement(String cardID) {
        switch (cardID) {
            case Bite.ID:
                return DorhysGnawing.DATA.ID;
            case RitualDagger.ID:
                return BlackKnife.DATA.ID;
        }
        return null;
    }

    @Override
    public String getReplacement(String cardID) {
        TemplateCardData data = TemplateCardData.getTemplate(cardID);
        return data != null ? data.ID : null;
    }

    @Override
    public ConjurerStrings getStrings() {
        return new ConjurerStrings(this);
    }

    @Override
    public ConjurerTooltips getTooltips() {
        return new ConjurerTooltips();
    }

    @Override
    public void initializeColor() {
        Color color = CardHelper.getColor(106, 210, 177);
        BaseMod.addColor(cardColor, color, color, color, color, color, color, color,
                images.attack, images.skill, images.power,
                images.orbA, images.attackL, images.skillL,
                images.powerL, images.orbB, images.orbC);
    }

    @Override
    protected void postInitialize() {
        super.postInitialize();
        CombatManager.playerSystem.registerMeter(playerClass, ConjurerReactionMeter.meter);
        EUI.addCardSetFilter(cardColor, GameUtilities::getLoadoutNameForCard);
        PCLAffinity.registerAvailableAffinities(cardColor, PCLAffinity.Red, PCLAffinity.Blue, PCLAffinity.Green, PCLAffinity.Orange);
        PCLAffinity.registerAffinityBorder(cardColor, PCLCoreImages.Core.borderSpecial2);
        PCLCardAlly.registerAnimation(cardColor, this::getAnimation);
        data.addTutorial(ConjurerTutorialMonster.DATA);
        PCLTutorialMonster.register(ConjurerSummonTutorialMonster.DATA, data.config.seenSummonTutorial, p -> p.chosenClass == data.resources.playerClass && EUIUtils.any(p.masterDeck.group, card -> card.type == PCLEnum.CardType.SUMMON));
    }

    // Purposely added to allow patching
    @Override
    public void receiveEditStrings() {
        super.receiveEditStrings();
    }
}

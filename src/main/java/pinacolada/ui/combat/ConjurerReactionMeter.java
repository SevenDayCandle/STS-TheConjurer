package pinacolada.ui.combat;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import extendedui.EUIRM;
import extendedui.EUIUtils;
import extendedui.ui.controls.EUIButton;
import extendedui.ui.controls.EUILabel;
import extendedui.ui.controls.EUITextBox;
import extendedui.ui.hitboxes.RelativeHitbox;
import extendedui.ui.tooltips.EUITooltip;
import extendedui.utilities.EUIColors;
import extendedui.utilities.EUIFontHelper;
import pinacolada.actions.PCLActions;
import pinacolada.actions.powers.ElementReaction;
import pinacolada.cards.base.*;
import pinacolada.misc.AffinityReactions;
import pinacolada.misc.CombatManager;
import pinacolada.misc.ConjurerUseInfo;
import pinacolada.misc.PCLUseInfo;
import pinacolada.orbs.PCLOrb;
import pinacolada.powers.PCLClickableUse;
import pinacolada.powers.conjurer.AbstractPCLElementalPower;
import pinacolada.powers.conjurer.FrostbitePower;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.resources.pcl.PCLCoreStrings;
import pinacolada.skills.PSkill;
import pinacolada.utilities.GameUtilities;
import pinacolada.utilities.PCLRenderHelpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static extendedui.EUIUtils.array;

public class ConjurerReactionMeter extends PCLPlayerMeter
{
    public static final String ID = createFullID(ConjurerResources.conjurer, PCLEmptyMeter.class);

    public static final Color ACTIVE_COLOR = new Color(0.5f, 1f, 0.5f, 1f);
    public static final float ICON_SIZE = scale(48);
    public static final float BASE_AMOUNT_SCALE = 1f;
    public static final float BASE_CHARGE_SCALE = 0.6f;
    public static final float BUTTON_SCALE = 1.5f;
    public static final float OFFSET_SCALE_X = 2.5f * BUTTON_SCALE;
    public static final float OFFSET_SCALE_Y = -0.5f * BUTTON_SCALE;
    public static final int STARTING_CHARGE = 2;
    public static final int MAX_CHARGE = 5;
    public static final ConjurerReactionMeter meter = new ConjurerReactionMeter();

    protected ArrayList<ConjurerElementButton> elements = new ArrayList<>();
    protected ConjurerElementButton fire;
    protected ConjurerElementButton air;
    protected ConjurerElementButton water;
    protected ConjurerElementButton earth;
    protected ConjurerElementButton light;
    protected ConjurerElementButton dark;
    protected EUIButton chargeImage;
    protected EUILabel chargeHeader;
    protected EUILabel reactionHeader;
    protected EUITextBox chargeText;
    protected EUITextBox reactionCountText;
    protected EUITooltip chargeTooltip;
    protected PCLClickableUse charges;
    protected int reactionCount;
    protected PCLAffinity lastUpgrade = PCLAffinity.General;
    private List<PCLCardAffinity> lastCardAffinities = new ArrayList<>();
    private List<AbstractPCLElementalPower> lastTargetPowers = new ArrayList<>();
    private int reactionPreview;


    public ConjurerReactionMeter()
    {
        super(ID, ConjurerResources.conjurer.config.meterPosition, ICON_SIZE);

        fire = new ConjurerElementButton(this, PCLAffinity.Red, ConjurerResources.conjurer.images.core.elementFire.texture(), RelativeHitbox.fromPercentages(hb, BUTTON_SCALE, BUTTON_SCALE, OFFSET_SCALE_X, OFFSET_SCALE_Y + BUTTON_SCALE));
        air = new ConjurerElementButton(this, PCLAffinity.Green,ConjurerResources.conjurer.images.core.elementAir.texture(), RelativeHitbox.fromPercentages(hb, BUTTON_SCALE, BUTTON_SCALE, OFFSET_SCALE_X, OFFSET_SCALE_Y- BUTTON_SCALE));
        water = new ConjurerElementButton(this, PCLAffinity.Blue, ConjurerResources.conjurer.images.core.elementWater.texture(), RelativeHitbox.fromPercentages(hb, BUTTON_SCALE, BUTTON_SCALE, OFFSET_SCALE_X + BUTTON_SCALE, OFFSET_SCALE_Y));
        earth = new ConjurerElementButton(this, PCLAffinity.Orange, ConjurerResources.conjurer.images.core.elementEarth.texture(), RelativeHitbox.fromPercentages(hb, BUTTON_SCALE, BUTTON_SCALE, OFFSET_SCALE_X - BUTTON_SCALE, OFFSET_SCALE_Y));
        light = new ConjurerElementButton(this, PCLAffinity.Yellow, ConjurerResources.conjurer.images.core.elementLight.texture(), RelativeHitbox.fromPercentages(hb, BUTTON_SCALE, BUTTON_SCALE, OFFSET_SCALE_X, OFFSET_SCALE_Y));
        dark = new ConjurerElementButton(this, PCLAffinity.Purple, ConjurerResources.conjurer.images.core.elementDark.texture(), RelativeHitbox.fromPercentages(hb, BUTTON_SCALE, BUTTON_SCALE, OFFSET_SCALE_X, OFFSET_SCALE_Y));
        elements.add(fire);
        elements.add(air);
        elements.add(water);
        elements.add(earth);
        elements.add(light);
        elements.add(dark);

        // Light/Dark are hidden
        light.setActive(false);
        dark.setActive(false);

        skips = new PCLClickableUse(this, (a, b) -> tryUseCharge(lastUpgrade, b), PCLCardTarget.Single, MAX_CHARGE, false, true);

        reactionHeader = new EUILabel(EUIFontHelper.cardtitlefontSmall,
                RelativeHitbox.fromPercentages(hb, 2, 2, 8f, 0.1f)).setLabel(ConjurerResources.conjurer.tooltips.reaction.title)
                .setFontScale(0.8f)
                .setAlignment(0.85f, 0.5f)
                .setTooltip(ConjurerResources.conjurer.tooltips.reaction);
        reactionCountText = new EUITextBox(EUIRM.images.panelEllipticalHalfH.texture(), RelativeHitbox.fromPercentages(hb, 2, 1.8f, 8f, -0.47f))
                .setColors(EUIColors.black(0.6f), Settings.CREAM_COLOR)
                .setAlignment(0.5f, 0.5f)
                .setFont(EUIFontHelper.cardtitlefontNormal, BASE_AMOUNT_SCALE);

        chargeTooltip = new EUITooltip(ConjurerResources.conjurer.tooltips.charge.title, ConjurerResources.conjurer.tooltips.charge.descriptions);

        chargeHeader = new EUILabel(EUIFontHelper.cardtitlefontSmall,
                RelativeHitbox.fromPercentages(hb, 2, 2, 10f, 0.1f)).setLabel(ConjurerResources.conjurer.tooltips.charge.title)
                .setFontScale(0.75f)
                .setAlignment(0.85f, 0.5f)
                .setTooltip(chargeTooltip);
        chargeImage = new EUIButton(PGR.core.images.unknown.texture(), new RelativeHitbox(chargeHeader.hb, ICON_SIZE * 0.75f, ICON_SIZE * 0.75f, chargeHeader.hb.width * 0.5f, chargeHeader.hb.height * 0.25f))
                .setTooltip(chargeTooltip)
                .setOnClick(() -> skips.targetToUse(1))
                .setOnRightClick(() -> skips.targetToUse(skips.getCurrentUses()));
        chargeText = new EUITextBox(EUIRM.images.panelEllipticalHalfH.texture(), RelativeHitbox.fromPercentages(chargeImage.hb, 1, 1, 0.5f, -0.25f))
                .setColors(EUIColors.black(0.6f), Settings.CREAM_COLOR)
                .setAlignment(0.5f, 0.5f)
                .setFont(EUIFontHelper.cardtitlefontNormal, 0.6f);
        enableCharges(false);
    }

    public void addCount(int amount)
    {
        addCount(amount, true);
    }

    public void addCount(int amount, boolean flash)
    {
        this.reactionPreview = this.reactionCount = this.reactionCount + amount;
        if (flash)
        {
            reactionCountText.label.setFontScale(8.0f);
        }
    }

    public void addLevel(PCLAffinity affinity, int amount)
    {
        final ConjurerElementButton p = getElementButton(affinity);
        if (p != null)
        {
            p.addLevel(amount);
        }
    }

    public void addSkip(int amount)
    {
        skips.addUses(amount);
        chargeText.label.setFontScale(4.8f);
    }

    public boolean canGlow(AbstractCard c)
    {
        return chargeImage.isActive;
    }

    public void disableAffinity(PCLAffinity affinity)
    {
        ConjurerElementButton button = getElementButton(affinity);
        if (button != null)
        {
            button.setEnabled(false);
        }
    }

    // TODO use this in a relic
    public void enableCharges(boolean value)
    {
        chargeHeader.setActive(value);
        chargeImage.setActive(value);
        chargeText.setActive(value);
    }

    public void flash(PCLAffinity affinity)
    {
        ConjurerElementButton button = getElementButton(affinity);
        if (button != null)
        {
            button.flash();
        }
    }

    @Override
    public PCLAffinity get(int target)
    {
        return getCurrentAffinity();
    }

    @Override
    public PCLAffinity getCurrentAffinity()
    {
        return lastUpgrade;
    }

    @Override
    public String[] getInfoDescription()
    {
        return array(PGR.core.strings.tutorial.affinityTutorial, PGR.core.strings.tutorial.conjurerTutorial1, PGR.core.strings.tutorial.conjurerTutorial2, PGR.core.strings.tutorial.conjurerTutorial3);
    }

    @Override
    public String getInfoMainDescrption()
    {
        return PGR.core.strings.tutorial.conjurerSimple;
    }

    @Override
    public String getInfoTitle()
    {
        return ConjurerResources.conjurer.tooltips.elementalDebuff.title;
    }

    @Override
    public int getLevel(PCLAffinity affinity)
    {
        if (affinity == PCLAffinity.General)
        {
            return EUIUtils.max(elements, r -> r.level);
        }
        final ConjurerElementButton p = getElementButton(affinity);
        return p == null ? 0 : p.level;
    }

    @Override
    public Object getRerollDescription()
    {
        return ConjurerResources.conjurer.tooltips.reaction.title;
    }

    @Override
    public boolean isHovered()
    {
        return super.isHovered() || EUIUtils.any(elements, p -> p.hb.hovered);
    }

    @Override
    public void renderImpl(SpriteBatch sb)
    {
        super.renderImpl(sb);

        for (ConjurerElementButton element : elements)
        {
            element.tryRender(sb);
        }

        reactionCountText.tryRender(sb);
        reactionHeader.tryRender(sb);
        chargeHeader.tryRender(sb);
        chargeImage.tryRender(sb);
        chargeText.tryRender(sb);
    }

    public void initialize()
    {
        super.initialize();
        for (ConjurerElementButton b : elements)
        {
            b.initialize();
        }
        reactionPreview = reactionCount = 0;
        skips.setUses(STARTING_CHARGE, MAX_CHARGE, false, true);
        set(GameUtilities.getRandomElement(PCLAffinity.getAvailableAffinities()), 0);
        if (lastUpgrade == null)
        {
            set(PCLAffinity.General, 0);
        }
        lastCardAffinities = new ArrayList<>();
        lastTargetPowers = new ArrayList<>();
        enableCharges(false);

        addDefaultReactions();

        water.addAdditionalPower(FrostbitePower.POWER_ID);
    }

    public void addDefaultReactions()
    {
        fire.addCombustion(water);
        air.addCombustion(earth);
        water.addCombustion(fire);
        earth.addCombustion(air);

        fire.addRedox(earth);
        air.addRedox(water);
        water.addRedox(air);
        earth.addRedox(fire);
    }

    public void addExtraReactions()
    {
        water.addCombustion(light);
        light.addCombustion(dark);
        light.addCombustion(earth);
        light.addRedox(water);
        earth.addRedox(light);
        light.addCombustion(dark);
        dark.addCombustion(light);
    }

    @Override
    public void updateImpl(PCLCard card, AbstractCreature target, boolean isDraggingCard, boolean shouldUpdateForCard, boolean shouldUpdateForTarget)
    {
        super.updateImpl(card, target, isDraggingCard, shouldUpdateForCard, shouldUpdateForTarget);
        boolean interactable = skips.interactable();

        for (ConjurerElementButton element : elements)
        {
            element.tryUpdate();
        }

        if ((shouldUpdateForTarget || shouldUpdateForCard) && card != null)
        {
            lastTargetPowers = card.pclTarget == PCLCardTarget.All || card.pclTarget == PCLCardTarget.AllEnemy ?
                    EUIUtils.flattenList(EUIUtils.map(GameUtilities.getEnemies(true), this::getElementalPowers))
                    : target != null && target.powers != null ? getElementalPowers(target)
                            : new ArrayList<>();
            if (shouldUpdateForCard)
            {
                lastCardAffinities = GameUtilities.getPCLCardAffinities(card).getCardAffinities(true);
            }

            reactionPreview = reactionCount;
            for (ConjurerElementButton element : elements)
            {
                reactionPreview += element.updatePreview(lastCardAffinities, lastTargetPowers);
            }
        }
        else if (card == null)
        {
            reactionPreview = reactionCount;
            for (ConjurerElementButton element : elements)
            {
                element.unsetPreview();
                if (element.hb.hovered && element.canIntensify())
                {
                    reactionPreview -= element.currentCost;
                }
            }
        }
        reactionHeader.tryUpdate();
        reactionCountText.label.setFontScale(PCLRenderHelpers.lerpScale(reactionCountText.label.fontScale, BASE_AMOUNT_SCALE));
        reactionCountText.setLabel(reactionPreview).setFontColor(isHighlighted() ? ACTIVE_COLOR : EUIColors.blue(1f)).tryUpdate();

        if (chargeHeader.isActive)
        {
            chargeHeader.update();
            chargeImage.setInteractable(interactable).tryUpdate();
            chargeText.setLabel(skips.getCurrentUses())
                    .setFontColor(interactable ? (CombatManager.canActivateSemiLimited(ConjurerElementButton.INCREASE_ID) ? Settings.GREEN_TEXT_COLOR : Settings.BLUE_TEXT_COLOR) : EUIColors.cream(0.6f))
                    .tryUpdate();
            chargeText.label.setFontScale(PCLRenderHelpers.lerpScale(chargeText.label.fontScale, BASE_CHARGE_SCALE));
        }

        boolean updateCharge = chargeImage.hb.justHovered || chargeHeader.hb.justHovered;
        skips.refresh(false, updateCharge);
        if (updateCharge)
        {
            EUITooltip helper = PCLElementHelper.get(lastUpgrade).getTooltip();
            chargeTooltip.setDescription(
                    EUIUtils.joinStrings(EUIUtils.SPLIT_LINE,
                            ConjurerResources.conjurer.tooltips.charge.descriptions.get(0),
                            PCLCoreStrings.leftClick(PSkill.capital(PGR.core.strings.actions.applyAmount(1, helper.getTitleOrIcon()), true)),
                            PCLCoreStrings.rightClick(PSkill.capital(PGR.core.strings.actions.applyAmount(skips.getCurrentUses(), helper.getTitleOrIcon()), true))));
        }
    }

    @Override
    public void onCardPlayed(PCLCard card, PCLUseInfo info, boolean fromSummon)
    {
        ConjurerUseInfo cInfo = EUIUtils.safeCast(info, ConjurerUseInfo.class);
        if (cInfo != null && !cInfo.reactions.isEmpty())
        {
            PCLActions.last.add(new ElementReaction(cInfo.reactions, card, info.source, info.target));
        }
    }

    @Override
    public PCLAffinity set(PCLAffinity affinity, int target)
    {
        lastUpgrade = affinity;
        EUITooltip helper = PCLElementHelper.get(lastUpgrade).getTooltip();
        chargeImage.setBackground(helper.icon.getTexture()).setTooltip(helper);
        return getCurrentAffinity();
    }

    public ConjurerElementButton getElementButton(PCLAffinity affinity)
    {
        return affinity.id >= 0 && affinity.id < elements.size() ? elements.get(affinity.id) : null;
    }

    public ArrayList<ConjurerElementButton> getElementButtons()
    {
        return elements;
    }

    private ArrayList<AbstractPCLElementalPower> getElementalPowers(AbstractCreature c)
    {
        return EUIUtils.mapAsNonnull(c.powers, po -> EUIUtils.safeCast(po, AbstractPCLElementalPower.class));
    }

    public ConjurerReactionButton getReactionButton(PCLAffinity dest, PCLAffinity target)
    {
        ConjurerElementButton destButton = getElementButton(dest);
        return destButton != null ? destButton.reactions.get(target) : null;
    }

    public int getReactionCount()
    {
        return reactionCount;
    }

    public AffinityReactions getReactions(AbstractCard card, Collection<? extends AbstractCreature> mo)
    {
        PCLCardAffinities affinities = GameUtilities.getPCLCardAffinities(card);
        if (affinities != null)
        {
            return getReactions(affinities.getCardAffinities(true), mo);
        }
        return new AffinityReactions();
    }

    public AffinityReactions getReactions(ArrayList<PCLCardAffinity> affs, Collection<? extends AbstractCreature> mo)
    {
        AffinityReactions reactions = new AffinityReactions();
        for (AbstractCreature m : mo)
        {
            if (m.powers != null)
            {
                for (AbstractPower po : m.powers)
                {
                    for (ConjurerElementButton button : getElementButtons())
                    {
                        if (button.matchesPower(po.ID))
                        {
                            for (PCLCardAffinity af : affs)
                            {
                                if (button.hasCombust(af.type))
                                {
                                    reactions.addCombust(button.affinity, af.type, button.reactionGain(po, af, ConjurerElementButton.Type.Combust));
                                }
                                if (button.hasRedox(af.type))
                                {
                                    reactions.addRedox(button.affinity, af.type, button.reactionGain(po, af, ConjurerElementButton.Type.Redox));
                                }
                            }
                        }
                    }
                }
            }
        }
        return reactions;
    }

    public float modifyBlock(float block, PCLCard source, PCLCard card, AbstractCreature target)
    {
        if (target != null) {
            float oldBlock = block;
            float multiplier = 100;
            for (PCLCardAffinity aff : source.affinities.getCardAffinities(true))
            {
                for (AbstractPower p : target.powers) {
                    for (ConjurerElementButton element : getElementButtons())
                    {
                        if (element.canRedox(aff.type, p.ID))
                        {
                            multiplier += AbstractPCLElementalPower.getAmplifyMultiplier(aff.type) * aff.level;
                        }
                    }
                }
            }
            block *= multiplier / 100;
            card.addDefendDisplay(PCLAffinity.General, oldBlock, block);
        }
        return block;
    }

    public float modifyDamage(float damage, PCLCard source, PCLCard card, AbstractCreature target)
    {
        if (target != null) {
            float oldDamage = damage;
            float multiplier = 100;
            for (PCLCardAffinity aff : source.affinities.getCardAffinities(true))
            {
                for (AbstractPower p : target.powers) {
                    for (ConjurerElementButton element : getElementButtons())
                    {
                        if (element.canCombust(aff.type, p.ID))
                        {
                            multiplier += AbstractPCLElementalPower.getAmplifyMultiplier(aff.type) * aff.level;
                        }
                    }
                }
            }
            damage *= multiplier / 100;
            card.addAttackDisplay(PCLAffinity.General, oldDamage, damage);
        }
        return damage;
    }

    public float modifyMagicNumber(float magicNumber, PCLCard source, PCLCard card)
    {
        return magicNumber;
    }

    public float modifyOrbOutput(float initial, AbstractCreature target, AbstractOrb orb)
    {
        if (orb instanceof PCLOrb)
        {
            float multiplier = 100;
            PCLAffinity aff = ((PCLOrb) orb).affinity;
            for (AbstractPower p : target.powers) {
                for (ConjurerElementButton element : getElementButtons())
                {
                    if (element.canCombust(aff, p.ID))
                    {
                        multiplier += AbstractPCLElementalPower.getAmplifyMultiplier(aff);
                    }
                }
            }
            initial *= multiplier / 100;
        }
        return initial;
    }

    public boolean trySpendCount(int amount)
    {
        if (reactionCount < amount)
        {
            return false;
        }
        addCount(-amount, false);
        return true;
    }

    public boolean tryUseCharge(PCLAffinity affinity, PCLUseInfo info)
    {
        int value = info.getData(1);
        if (affinity != null && info.target != null)
        {
            PCLElementHelper helper = PCLElementHelper.get(affinity);
            PCLActions.bottom.applyPower(info.target, PCLCardTarget.Single, helper, value);
            return true;
        }
        return false;
    }

    public boolean isPowerElemental(String id) {
        return EUIUtils.any(elements, e -> e.matchesPower(id));
    }

    public boolean isPowerElemental(String id, PCLAffinity affinity) {
        if (affinity == null) {
            return isPowerElemental(id);
        }
        ConjurerElementButton button = getElementButton(affinity);
        return button != null && button.matchesPower(id);
    }

    public boolean isHighlighted()
    {
        return reactionPreview != reactionCount;
    }

    public int getPreviewGain()
    {
        return reactionPreview - reactionCount;
    }

    public EUITooltip getTooltip()
    {
        return chargeTooltip;
    }
}

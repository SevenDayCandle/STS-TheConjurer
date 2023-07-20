package pinacolada.ui.combat;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import extendedui.EUIRM;
import extendedui.EUIUtils;
import extendedui.ui.controls.*;
import extendedui.ui.hitboxes.RelativeHitbox;
import extendedui.ui.tooltips.EUIKeywordTooltip;
import extendedui.ui.tooltips.EUITooltip;
import extendedui.utilities.EUIColors;
import extendedui.utilities.EUIFontHelper;
import pinacolada.actions.PCLActions;
import pinacolada.actions.powers.ElementReaction;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardAffinities;
import pinacolada.cards.base.fields.PCLCardAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.characters.ConjurerCharacter;
import pinacolada.dungeon.CombatManager;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.misc.AffinityReactions;
import pinacolada.misc.ConjurerUseInfo;
import pinacolada.orbs.PCLOrb;
import pinacolada.powers.PCLClickableUse;
import pinacolada.powers.conjurer.AbstractPCLElementalPower;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.resources.pcl.PCLCoreImages;
import pinacolada.resources.pcl.PCLCoreStrings;
import pinacolada.skills.PSkill;
import pinacolada.utilities.GameUtilities;
import pinacolada.utilities.PCLRenderHelpers;

import java.util.*;

import static extendedui.EUIUtils.array;

public class ConjurerReactionMeter extends PCLPlayerMeter {
    private static final HashMap<String, Set<PCLAffinity>> CARD_AFFINITIES = new HashMap<>();
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
    private AffinityReactions previewReactions;
    private int matterPreview;
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
    protected int matterCount;
    protected int totalReactions;
    protected PCLAffinity lastUpgrade = PCLAffinity.General;
    protected PCLClickableUse skips;


    public ConjurerReactionMeter() {
        super(ID, ConjurerResources.conjurer.data.config.meterPosition, ICON_SIZE);

        fire = new ConjurerElementButton(this, PCLAffinity.Red, ConjurerResources.conjurer.images.core.elementFire.texture(), RelativeHitbox.fromPercentages(hb, BUTTON_SCALE, BUTTON_SCALE, OFFSET_SCALE_X, OFFSET_SCALE_Y + BUTTON_SCALE));
        air = new ConjurerElementButton(this, PCLAffinity.Green, ConjurerResources.conjurer.images.core.elementAir.texture(), RelativeHitbox.fromPercentages(hb, BUTTON_SCALE, BUTTON_SCALE, OFFSET_SCALE_X, OFFSET_SCALE_Y - BUTTON_SCALE));
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

        skips = new PCLClickableUse(this, (a, b, c) -> tryUseCharge(lastUpgrade, b, c), PCLCardTarget.Single, MAX_CHARGE, false, true);

        reactionHeader = new EUILabel(EUIFontHelper.cardTitleFontSmall,
                RelativeHitbox.fromPercentages(hb, 2, 2, 8f, 0.1f)).setLabel(ConjurerResources.conjurer.tooltips.matter.title)
                .setFontScale(0.8f)
                .setAlignment(0.85f, 0.5f)
                .setTooltip(ConjurerResources.conjurer.tooltips.matter);
        reactionCountText = new EUITextBox(EUIRM.images.panelEllipticalHalfH.texture(), RelativeHitbox.fromPercentages(hb, 2, 1.8f, 8f, -0.47f))
                .setColors(EUIColors.black(0.6f), Settings.CREAM_COLOR)
                .setAlignment(0.5f, 0.5f)
                .setFont(EUIFontHelper.cardTitleFontNormal, BASE_AMOUNT_SCALE);

        chargeTooltip = new EUITooltip(ConjurerResources.conjurer.tooltips.charge.title, ConjurerResources.conjurer.tooltips.charge.description);

        chargeHeader = new EUILabel(EUIFontHelper.cardTitleFontSmall,
                RelativeHitbox.fromPercentages(hb, 2, 2, 10f, 0.1f)).setLabel(ConjurerResources.conjurer.tooltips.charge.title)
                .setFontScale(0.75f)
                .setAlignment(0.85f, 0.5f)
                .setTooltip(chargeTooltip);
        chargeImage = new EUIButton(PCLCoreImages.CardAffinity.unknown.texture(), new RelativeHitbox(chargeHeader.hb, ICON_SIZE * 0.75f, ICON_SIZE * 0.75f, chargeHeader.hb.width * 0.5f, chargeHeader.hb.height * 0.25f))
                .setTooltip(chargeTooltip)
                .setOnClick(() -> skips.targetToUse(1))
                .setOnRightClick(() -> skips.targetToUse(skips.getCurrentUses()));
        chargeText = new EUITextBox(EUIRM.images.panelEllipticalHalfH.texture(), RelativeHitbox.fromPercentages(chargeImage.hb, 1, 1, 0.5f, -0.25f))
                .setColors(EUIColors.black(0.6f), Settings.CREAM_COLOR)
                .setAlignment(0.5f, 0.5f)
                .setFont(EUIFontHelper.cardTitleFontNormal, 0.6f);
        enableCharges(false);
    }

    public void addCount(int amount) {
        addCount(amount, true);
    }

    public void addCount(int amount, boolean flash) {
        this.matterPreview = this.matterCount = this.matterCount + amount;
        if (flash) {
            reactionCountText.label.setFontScale(8.0f);
        }
    }

    public void addDefaultReactions() {
        fire.addReaction(water);
        water.addReaction(air);
        air.addReaction(earth);
        earth.addReaction(fire);
    }

    public void addLevel(PCLAffinity affinity, int amount) {
        final ConjurerElementButton p = getElementButton(affinity);
        if (p != null) {
            p.addLevel(amount);
        }
    }

    public void addSkip(int amount) {
        skips.addUses(amount);
        chargeText.label.setFontScale(4.8f);
    }

    public boolean canGlow(AbstractCard c) {
        return chargeImage.isActive;
    }

    public void disableAffinity(PCLAffinity affinity) {
        ConjurerElementButton button = getElementButton(affinity);
        if (button != null) {
            button.setEnabled(false);
        }
    }

    @Override
    public ConjurerUseInfo generateInfo(AbstractCard card, AbstractCreature source, AbstractCreature target) {
        return new ConjurerUseInfo(card, source, target);
    }

    @Override
    public PCLAffinity get(int target) {
        return getCurrentAffinity();
    }

    @Override
    public PCLAffinity getCurrentAffinity() {
        return lastUpgrade;
    }

    public EUITooltip getTooltip() {
        return chargeTooltip;
    }

    @Override
    public int getLevel(PCLAffinity affinity) {
        if (affinity == PCLAffinity.General) {
            return EUIUtils.max(elements, r -> r.level);
        }
        final ConjurerElementButton p = getElementButton(affinity);
        return p == null ? 0 : p.level;
    }

    @Override
    public Object getRerollDescription() {
        return ConjurerResources.conjurer.tooltips.matter.title;
    }

    public void initialize() {
        super.initialize();
        for (ConjurerElementButton b : elements) {
            b.initialize();
        }
        matterPreview = matterCount = 0;
        skips.setUses(STARTING_CHARGE, MAX_CHARGE, false, true);
        set(GameUtilities.getRandomElement(PCLAffinity.getAvailableAffinities()), 0);
        if (lastUpgrade == null) {
            set(PCLAffinity.General, 0);
        }
        previewReactions = null;
        totalReactions = 0;
        enableCharges(false);

        addDefaultReactions();
    }

    @Override
    public boolean isHovered() {
        return super.isHovered() || EUIUtils.any(elements, p -> p.hb.hovered);
    }

    @Override
    public void renderImpl(SpriteBatch sb) {
        super.renderImpl(sb);

        for (ConjurerElementButton element : elements) {
            element.tryRender(sb);
        }

        reactionCountText.tryRender(sb);
        reactionHeader.tryRender(sb);
        chargeHeader.tryRender(sb);
        chargeImage.tryRender(sb);
        chargeText.tryRender(sb);
    }

    @Override
    public void updateImpl(PCLCard card, PCLCard originalCard, AbstractCreature target, AbstractCreature originalTarget, boolean isDraggingCard, boolean shouldUpdateForCard, boolean shouldUpdateForTarget) {
        super.updateImpl(card, originalCard, target, originalTarget, isDraggingCard, shouldUpdateForCard, shouldUpdateForTarget);
        boolean interactable = skips.interactable();

        for (ConjurerElementButton element : elements) {
            element.tryUpdate();
        }

        if ((shouldUpdateForTarget || shouldUpdateForCard) && card != null) {
            previewReactions = getReactions(card, card.pclTarget.getTargets(AbstractDungeon.player, target));

            int sum = isSwapIntended(card, originalCard) ? previewReactions.sum() * CombatManager.summons.triggerTimes : previewReactions.sum();
            matterPreview = matterCount + sum;
            for (ConjurerElementButton element : elements) {
                element.updatePreview(previewReactions);
            }
        }
        else if (card == null) {
            matterPreview = matterCount;
            for (ConjurerElementButton element : elements) {
                element.unsetPreview();
                if (element.hb.hovered && element.canIntensify()) {
                    matterPreview -= element.currentCost;
                }
            }
        }
        reactionHeader.tryUpdate();
        reactionCountText.label.setFontScale(PCLRenderHelpers.lerpScale(reactionCountText.label.fontScale, BASE_AMOUNT_SCALE));
        reactionCountText.setLabel(matterPreview).setFontColor(isHighlighted() ? ACTIVE_COLOR : EUIColors.blue(1f)).tryUpdate();

        if (chargeHeader.isActive) {
            chargeHeader.update();
            chargeImage.setInteractable(interactable).tryUpdate();
            chargeText.setLabel(skips.getCurrentUses())
                    .setFontColor(interactable ? (CombatManager.canActivateSemiLimited(ConjurerElementButton.INCREASE_ID) ? Settings.GREEN_TEXT_COLOR : Settings.BLUE_TEXT_COLOR) : EUIColors.cream(0.6f))
                    .tryUpdate();
            chargeText.label.setFontScale(PCLRenderHelpers.lerpScale(chargeText.label.fontScale, BASE_CHARGE_SCALE));
        }

        boolean updateCharge = chargeImage.hb.justHovered || chargeHeader.hb.justHovered;
        skips.refresh(false, updateCharge);
        if (updateCharge) {
            EUITooltip helper = PCLElementHelper.get(lastUpgrade).getTooltip();
            chargeTooltip.setDescription(
                    EUIUtils.joinStrings(EUIUtils.SPLIT_LINE,
                            ConjurerResources.conjurer.tooltips.charge.description,
                            PCLCoreStrings.leftClick(PSkill.capital(PGR.core.strings.act_applyAmountX(1, helper.getTitleOrIcon()), true)),
                            PCLCoreStrings.rightClick(PSkill.capital(PGR.core.strings.act_applyAmountX(skips.getCurrentUses(), helper.getTitleOrIcon()), true))));
        }
    }

    public float modifyBlock(float block, PCLCard source, PCLCard card, AbstractCreature target) {
        if (target != null) {
            float oldBlock = block;
            float multiplier = 100;
            for (PCLCardAffinity aff : source.affinities.getCardAffinities(true)) {
                for (AbstractPower p : target.powers) {
                    for (ConjurerElementButton element : getElementButtons()) {
                        if (element.canReact(aff.type, p.ID)) {
                            {
                                multiplier += AbstractPCLElementalPower.getAmplifyMultiplier(aff.type) * aff.level;
                            }
                        }
                    }
                }
            }
            block *= multiplier / 100;
            card.addDefendDisplay(PCLAffinity.General, oldBlock, block);
        }
        return block;
    }

    public float modifyDamage(float damage, PCLCard source, PCLCard card, AbstractCreature target) {
        if (target != null) {
            float oldDamage = damage;
            float multiplier = 100;
            for (PCLCardAffinity aff : source.affinities.getCardAffinities(true)) {
                for (AbstractPower p : target.powers) {
                    for (ConjurerElementButton element : getElementButtons()) {
                        if (element.canReact(aff.type, p.ID)) {
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

    public float modifyOrbOutput(float initial, AbstractCreature target, AbstractOrb orb) {
        if (orb instanceof PCLOrb) {
            float multiplier = 100;
            PCLAffinity aff = ((PCLOrb) orb).affinity;
            for (AbstractPower p : target.powers) {
                for (ConjurerElementButton element : getElementButtons()) {
                    if (element.canReact(aff, p.ID)) {
                        multiplier += AbstractPCLElementalPower.getAmplifyMultiplier(aff);
                    }
                }
            }
            initial *= multiplier / 100;
        }
        return initial;
    }

    public void onCardCreated(AbstractCard card, boolean startOfBattle) {
        super.onCardCreated(card, startOfBattle);
        if (!(card instanceof PCLCard)) {
            applyAffinities(card);
        }
    }

    @Override
    public void onCardPlayed(PCLCard card, PCLUseInfo info, boolean fromSummon) {
        ConjurerUseInfo cInfo = EUIUtils.safeCast(info, ConjurerUseInfo.class);
        if (cInfo != null && !cInfo.reactions.isEmpty()) {
            PCLActions.last.add(new ElementReaction(cInfo.reactions, card, info.source, info.target));
        }
    }

    @Override
    public PCLAffinity set(PCLAffinity affinity, int target) {
        lastUpgrade = affinity;
        EUIKeywordTooltip helper = PCLElementHelper.get(lastUpgrade).getTooltip();
        chargeImage.setBackground(helper.icon.getTexture()).setTooltip(helper);
        return getCurrentAffinity();
    }

    @Override
    public String getInfoMainDescrption() {
        return ConjurerResources.conjurer.strings.conjurerSimple;
    }

    @Override
    public EUITutorialPage[] getInfoPages() {
        return array(
                new EUITutorialImagePage(makeTitle(PGR.core.strings.misc_fabricate, PGR.core.tooltips.affinityGeneral.title), PGR.core.strings.tutorial_affinityTutorial, ConjurerResources.conjurer.images.tutorial.afftut01.texture()),
                new EUITutorialImagePage(makeTitle(PGR.core.strings.misc_fabricate, PGR.core.tooltips.summon.title, 1), PGR.core.strings.tutorial_summonTutorial1, ConjurerResources.conjurer.images.tutorial.ctut01.texture()),
                new EUITutorialImagePage(makeTitle(PGR.core.strings.misc_fabricate, PGR.core.tooltips.summon.title, 2), PGR.core.strings.tutorial_summonTutorial2, ConjurerResources.conjurer.images.tutorial.ctut02.texture()),
                new EUITutorialImagePage(makeTitle(PGR.core.strings.misc_fabricate, PGR.core.tooltips.summon.title, 3), PGR.core.strings.tutorial_summonTutorial3, ConjurerResources.conjurer.images.tutorial.ctut03.texture()),
                new EUITutorialImagePage(makeTitle(PGR.core.strings.misc_fabricate, PGR.core.tooltips.summon.title, 4), PGR.core.strings.tutorial_summonTutorial4, ConjurerResources.conjurer.images.tutorial.ctut03.texture()),
                new EUITutorialImagePage(makeTitle(PGR.core.strings.misc_fabricate, PGR.core.tooltips.summon.title, 5), PGR.core.strings.tutorial_summonTutorial5, ConjurerResources.conjurer.images.tutorial.ctut04.texture()),
                new EUITutorialImagePage(makeTitle(PGR.core.strings.misc_fabricate, PGR.core.tooltips.summon.title, 6), PGR.core.strings.tutorial_summonTutorial6, ConjurerResources.conjurer.images.tutorial.ctut05.texture()),
                new EUITutorialImagePage(makeTitle(PGR.core.strings.misc_fabricate, PGR.core.tooltips.summon.title, 7), PGR.core.strings.tutorial_summonTutorial7, ConjurerResources.conjurer.images.tutorial.ctut06.texture()),
                new EUITutorialImagePage(makeTitle(ConjurerCharacter.NAMES[0], ConjurerResources.conjurer.tooltips.elementalDebuff.title), ConjurerResources.conjurer.strings.conjurerTutorial1, ConjurerResources.conjurer.images.tutorial.etut01.texture()),
                new EUITutorialImagePage(makeTitle(ConjurerCharacter.NAMES[0], ConjurerResources.conjurer.tooltips.reaction.title, 1), ConjurerResources.conjurer.strings.conjurerTutorial2, ConjurerResources.conjurer.images.tutorial.etut02.texture()),
                new EUITutorialImagePage(makeTitle(ConjurerCharacter.NAMES[0], ConjurerResources.conjurer.tooltips.reaction.title, 2), ConjurerResources.conjurer.strings.conjurerTutorial3, ConjurerResources.conjurer.images.tutorial.etut02.texture()),
                new EUITutorialImagePage(makeTitle(ConjurerCharacter.NAMES[0], ConjurerResources.conjurer.tooltips.matter.title), ConjurerResources.conjurer.strings.conjurerTutorial4, ConjurerResources.conjurer.images.tutorial.etut03.texture())
        );
    }

    @Override
    public String getInfoTitle() {
        return ConjurerCharacter.NAMES[0];
    }

    // TODO check for custom card attributes
    public void applyAffinities(AbstractCard card) {

        Set<PCLAffinity> affinities = CARD_AFFINITIES.get(card.cardID);
        if (affinities == null) {
            affinities = new HashSet<>();

            if (idHas(card, "Fire", "Flame", "Burn", "Scorch", "Heat", "Solar")) {
                affinities.add(PCLAffinity.Red);
            }
            if (idHas(card, "Lava", "Magma")) {
                affinities.add(PCLAffinity.Red);
                affinities.add(PCLAffinity.Orange);
            }
            if (idHas(card, "Water", "Ice", "Icicle", "Snow", "Frost", "Chill", "Cold", "Freeze", "Aqua", "Ocean", "Bubble", "Liquid")) {
                affinities.add(PCLAffinity.Blue);
            }
            if (idHas(card, "Storm", "Mist", "Fog")) {
                affinities.add(PCLAffinity.Blue);
                affinities.add(PCLAffinity.Green);
            }
            if (idHas(card, "Mud", "Swamp")) {
                affinities.add(PCLAffinity.Blue);
                affinities.add(PCLAffinity.Orange);
            }
            if (idHas(card, "Wind", "Sky", "Poison", "Toxic", "Air", "Smoke")) {
                affinities.add(PCLAffinity.Green);
            }
            if (idHas(card, "Leaf", "Nature", "Wood", "Forest", "Grass", "Blossom", "Bloom", "Plant", "Tree")) {
                affinities.add(PCLAffinity.Green);
                affinities.add(PCLAffinity.Orange);
            }
            if (idHas(card, "Earth", "Rock", "Stone", "Ground", "Land")) {
                affinities.add(PCLAffinity.Orange);
            }
            if (idHas(card, "Electric", "Thunder", "Lightning", "Shock", "Holy", "Bless", "Sacred")) {
                affinities.add(PCLAffinity.Yellow);
            }
            if (idHas(card, "Dark", "Shadow", "Evil", "Night", "Curse", "Void", "Corrupt", "infinitespire")) {
                affinities.add(PCLAffinity.Purple);
            }
            if (idHas(card, "Rainbow")) {
                affinities.add(PCLAffinity.Star);
            }
        }

        for (PCLAffinity af : affinities) {
            GameUtilities.modifyAffinityLevel(card, af, 1, true);
        }

    }

    // TODO use this in a relic
    public void enableCharges(boolean value) {
        chargeHeader.setActive(value);
        chargeImage.setActive(value);
        chargeText.setActive(value);
    }

    protected void fillReactions(AffinityReactions reactions, ArrayList<PCLCardAffinity> affs, Collection<? extends AbstractCreature> mo) {
        for (AbstractCreature m : mo) {
            if (m.powers != null) {
                for (AbstractPower po : m.powers) {
                    for (ConjurerElementButton button : getElementButtons()) {
                        if (button.matchesPower(po.ID)) {
                            for (PCLCardAffinity af : affs) {
                                if (button.hasReact(af.type)) {
                                    reactions.addReaction(button.affinity, af.type, button.reactionGain(po, af));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void flash(PCLAffinity affinity) {
        ConjurerElementButton button = getElementButton(affinity);
        if (button != null) {
            button.flash();
        }
    }

    public int getAmplifyOffset(PCLAffinity affinity) {
        ConjurerElementButton destButton = getElementButton(affinity);
        return destButton != null ? destButton.currentAmplifyOffset : 0;
    }

    public ConjurerElementButton getElementButton(PCLAffinity affinity) {
        return affinity.id >= 0 && affinity.id < elements.size() ? elements.get(affinity.id) : null;
    }

    public ArrayList<ConjurerElementButton> getElementButtons() {
        return elements;
    }

    private ArrayList<AbstractPCLElementalPower> getElementalPowers(AbstractCreature c) {
        return c != null && c.powers != null ? EUIUtils.mapAsNonnull(c.powers, po -> EUIUtils.safeCast(po, AbstractPCLElementalPower.class)) : new ArrayList<>();
    }

    public int getMatter() {
        return matterCount;
    }

    public int getPreviewGain() {
        return matterPreview - matterCount;
    }

    public AffinityReactions getPreviewReactions() {
        return previewReactions;
    }

    public ConjurerReactionButton getReactionButton(PCLAffinity dest, PCLAffinity target) {
        ConjurerElementButton destButton = getElementButton(dest);
        return destButton != null ? destButton.reactions.get(target) : null;
    }

    public AffinityReactions getReactions(AbstractCard card, Collection<? extends AbstractCreature> mo) {
        PCLCardAffinities affinities = GameUtilities.getPCLCardAffinities(card);
        if (affinities != null) {
            return getReactions(affinities.getCardAffinities(true), mo);
        }
        return new AffinityReactions();
    }

    public AffinityReactions getReactions(ArrayList<PCLCardAffinity> affs, Collection<? extends AbstractCreature> mo) {
        AffinityReactions reactions = new AffinityReactions();
        fillReactions(reactions, affs, mo);
        return reactions;
    }

    public int getTotalReactionsMade() {
        return totalReactions;
    }

    public void hideAffinity(PCLAffinity affinity) {
        ConjurerElementButton button = getElementButton(affinity);
        if (button != null) {
            button.setActive(false);
            button.setEnabled(false);
        }
    }

    protected boolean idHas(AbstractCard card, String... matches) {
        return EUIUtils.any(matches, card.cardID::contains);
    }

    public boolean isHighlighted() {
        return matterPreview != matterCount;
    }

    public boolean isPowerElemental(String id, PCLAffinity affinity) {
        if (affinity == null) {
            return isPowerElemental(id);
        }
        ConjurerElementButton button = getElementButton(affinity);
        return button != null && button.matchesPower(id);
    }

    public boolean isPowerElemental(String id) {
        return EUIUtils.any(elements, e -> e.matchesPower(id));
    }

    public void onReaction(AffinityReactions reactions) {
        if (reactions.hasReaction()) {
            totalReactions += 1;
        }
    }

    public void renderForTutorial(SpriteBatch sb, float x, float y) {
        for (ConjurerElementButton element : elements) {
            element.renderForTutorial(sb, x + element.hb.getOffsetX(), x + element.hb.getOffsetY());
        }
    }

    public boolean trySpendMatter(int amount) {
        if (matterCount < amount) {
            return false;
        }
        addCount(-amount, false);
        return true;
    }

    public boolean tryUseCharge(PCLAffinity affinity, PCLUseInfo info, PCLActions order) {
        Integer value = info.getData(Integer.class);
        if (value == null) {
            value = 1;
        }
        if (affinity != null && info.target != null) {
            PCLElementHelper helper = PCLElementHelper.get(affinity);
            order.applyPower(info.target, helper, value);
            return true;
        }
        return false;
    }

    public AffinityReactions updateReactions(AffinityReactions reactions, AbstractCard card, Collection<? extends AbstractCreature> mo) {
        reactions.clear();
        PCLCardAffinities affinities = GameUtilities.getPCLCardAffinities(card);
        if (affinities != null) {
            updateReactions(reactions, affinities.getCardAffinities(true), mo);
        }
        return reactions;
    }

    public AffinityReactions updateReactions(AffinityReactions reactions, ArrayList<PCLCardAffinity> affs, Collection<? extends AbstractCreature> mo) {
        reactions.clear();
        fillReactions(reactions, affs, mo);
        return reactions;
    }
}

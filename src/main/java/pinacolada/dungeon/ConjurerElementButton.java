package pinacolada.dungeon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import extendedui.EUIRenderHelpers;
import extendedui.EUIUtils;
import extendedui.ui.controls.EUIButton;
import extendedui.ui.controls.EUIImage;
import extendedui.ui.hitboxes.EUIHitbox;
import extendedui.ui.tooltips.EUIKeywordTooltip;
import extendedui.utilities.EUIColors;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardAffinity;
import pinacolada.effects.PCLEffects;
import pinacolada.effects.affinity.GenericFlashEffect;
import pinacolada.interfaces.subscribers.OnTryElementReactSubscriber;
import pinacolada.powers.conjurer.AbstractPCLElementalPower;
import pinacolada.powers.conjurer.ElementPowerData;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerImages;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.resources.pcl.PCLCoreStrings;
import pinacolada.utilities.GameUtilities;
import pinacolada.utilities.PCLRenderHelpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ConjurerElementButton extends EUIButton {
    public static final Color ACTIVE_COLOR = new Color(0.5f, 1f, 0.5f, 1f);
    public static final int BASE_COST = 20;
    public static final int BASE_COST_RATE = 10;
    public static final float BASE_AMOUNT_SCALE = 1f;
    protected final HashMap<PCLAffinity, ConjurerReactionButton> reactions = new HashMap<>();
    public final ElementPowerData power;
    public final ArrayList<String> additionalPowers = new ArrayList<>();
    public final ConjurerReactionMeter meter;
    private boolean busy;
    protected EUIImage elementImage;
    protected boolean reactionEnabled;
    protected float intensifyFontScale = BASE_AMOUNT_SCALE;
    protected int level;
    protected EUIKeywordTooltip keyword;
    protected float currentCostMultiplier = 1f;
    public boolean canInteract;
    public int currentCost;
    public int currentAmplifyOffset;

    public ConjurerElementButton(ConjurerReactionMeter meter, ElementPowerData power, Texture texture, EUIHitbox hb) {
        super(ConjurerImages.Core.squareBg.texture(), hb);
        this.meter = meter;
        this.power = power;
        elementImage = new EUIImage(texture, hb).setColor(Color.GRAY).setScale(0.625f, 0.625f);
        keyword = new EUIKeywordTooltip(power.strings.NAME);

        setOnClick(this::manualAddLevel);
        setTooltip(keyword);
    }

    public void addAdditionalPower(String powerID) {
        this.additionalPowers.add(powerID);
    }

    public void addLevel(int amount) {
        level += amount;
        currentCost += level * BASE_COST_RATE * currentCostMultiplier;
    }

    public ConjurerElementButton addReaction(PCLAffinity affinity) {
        return addReaction(meter.getElementButton(affinity));
    }

    public ConjurerElementButton addReaction(ConjurerElementButton reactor) {
        reactions.put(reactor.power.affinity, new ConjurerReactionButton(this, reactor));
        return this;
    }

    public boolean canIntensify() {
        return meter.getMatter() >= currentCost;
    }

    public boolean canReact(PCLAffinity affinity, AbstractCreature m) {
        return hasReact(affinity) && m != null && willReact(m);
    }

    public boolean canReact(PCLAffinity affinity, String powerID) {
        return hasReact(affinity) && matchesPower(powerID);
    }

    public void flash() {
        intensifyFontScale = 8.0f;
        //ElementImage.SetColor(Color.WHITE);
        PCLEffects.List.add(new GenericFlashEffect(elementImage.texture, this.hb.cX, this.hb.cY, true).setScale(Settings.scale * 0.5f));
    }

    public float getCurrentCostMultiplier() {
        return currentCostMultiplier;
    }

    public Set<PCLAffinity> getReactAffinities() {
        Set<PCLAffinity> affinities = new HashSet<>();
        for (ConjurerReactionButton button : reactions.values()) {
            affinities.add(button.source.power.affinity);
        }
        return affinities;
    }

    public boolean hasReact(PCLAffinity affinity) {
        return affinity == PCLAffinity.Star || (hasReaction(affinity));
    }

    public boolean hasReaction(PCLAffinity affinity) {
        return affinity == PCLAffinity.Star || reactions.containsKey(affinity);
    }

    public void initialize() {
        setEnabled(true);
        additionalPowers.clear();
        reactions.clear();
        level = 0;
        currentCost = BASE_COST;
        currentAmplifyOffset = 0;
        currentCostMultiplier = 1f;
        elementImage.setColor(Color.GRAY);
        canInteract = true;
        busy = false;
    }

    public void manualAddLevel() {
        if (!busy && reactionEnabled && canInteract) {
            busy = true;
            PCLActions.bottom.callback(() -> {
                meter.set(power.affinity, 0);
                tryAddLevel();
                busy = false;
            });
        }
    }

    public boolean matchesPower(String id) {
        return power.ID.equals(id) || EUIUtils.any(additionalPowers, s -> s.equals(id));
    }

    public int reactionGain(AbstractPower po, PCLCardAffinity cAff) {
        return CombatManager.subscriberInout(OnTryElementReactSubscriber.class, po.amount * cAff.level, (s, d) -> s.onTryElementReact(d, power.affinity, cAff.type));
    }

    public void renderForTutorial(SpriteBatch sb, float x, float y) {
        super.renderImpl(sb);
        elementImage.renderCentered(sb, x, y, hb.width, hb.height);
    }

    public void renderForTutorialWithArrows(SpriteBatch sb, float x, float y) {
        renderForTutorial(sb, x, y);
        for (ConjurerReactionButton button : reactions.values()) {
            button.renderCentered(sb, x + button.hb.getOffsetX(), y + button.hb.getOffsetY(), button.hb.width, button.hb.height);
        }
    }

    @Override
    public void renderImpl(SpriteBatch sb) {
        super.renderImpl(sb);
        PCLRenderHelpers.drawGrayscaleIf(sb, s -> elementImage.renderCentered(s, hb), busy || !canInteract);
        for (ConjurerReactionButton button : reactions.values()) {
            button.tryRenderCentered(sb);
        }

        FontHelper.renderFontCentered(sb, FontHelper.powerAmountFont, "L" + level, hb.cX + scale(15), hb.cY - scale(15), EUIColors.blue(1f), intensifyFontScale);
    }

    public void setCurrentCostMultiplier(float mult) {
        currentCost *= mult / currentCostMultiplier;
        currentCostMultiplier = mult;
    }

    public void setEnabled(boolean value) {
        reactionEnabled = value;
        elementImage.setShaderMode(reactionEnabled ? EUIRenderHelpers.ShaderMode.Normal : EUIRenderHelpers.ShaderMode.Grayscale);
        for (ConjurerReactionButton button : reactions.values()) {
            button.setActive(reactionEnabled);
        }
    }

    public void setLevelEnabled(boolean value) {
        canInteract = value;
    }

    public void tryAddLevel() {
        if (meter.trySpendMatter(currentCost)) {
            addLevel(1);
            PCLEffects.Queue.add(new GenericFlashEffect(elementImage.texture, this.hb.cX, this.hb.cY, true).setScale(Settings.scale * 0.5f));
            CombatManager.onIncreaseAffinityLevel(power.affinity);
        }
    }

    public void unsetPreview() {
        for (ConjurerReactionButton button : reactions.values()) {
            if (!button.hb.hovered) {
                button.unhighlight();
            }
        }
    }

    public void updateDescription() {
        if (PGR.isLoaded()) {
            ArrayList<String> strings = new ArrayList<>();
            strings.add(PCLCoreStrings.headerString(PGR.core.tooltips.level.title, level));
            strings.add(PCLCoreStrings.headerString(ConjurerResources.conjurer.strings.combat_conjurerMeterCost, currentCost));
            strings.add(PGR.core.strings.combat_effect(EUIUtils.format(power.strings.DESCRIPTIONS[1], PCLRenderHelpers.decimalFormat(AbstractPCLElementalPower.getIntensifyMultiplier(power)))
                    + " " + EUIUtils.format(ConjurerResources.conjurer.strings.combat_conjurerMeterBonus, power.affinity.getTooltip(), PCLRenderHelpers.decimalFormat(AbstractPCLElementalPower.getAmplifyMultiplier(level, currentAmplifyOffset)))));
            strings.add(EUIUtils.SPLIT_LINE + PCLCoreStrings.colorString("p", PGR.core.strings.combat_nextLevelEffect));
            strings.add(PCLCoreStrings.headerString(PGR.core.tooltips.level.title, level + 1));
            strings.add(PGR.core.strings.combat_effect(EUIUtils.format(power.strings.DESCRIPTIONS[1], PCLRenderHelpers.decimalFormat(AbstractPCLElementalPower.getIntensifyMultiplier(power, level + 1)))
                    + " " + EUIUtils.format(ConjurerResources.conjurer.strings.combat_conjurerMeterBonus, power.affinity.getTooltip(), PCLRenderHelpers.decimalFormat(AbstractPCLElementalPower.getAmplifyMultiplier(level + 1, currentAmplifyOffset))))
            );
            if (canIntensify()) {
                strings.add(EUIUtils.SPLIT_LINE + EUIUtils.format(ConjurerResources.conjurer.strings.combat_conjurerMeterNextIntensity, currentCost, power));
            }

            keyword.setIcon(power.getTooltip().icon);
            keyword.setDescription(EUIUtils.joinStrings(EUIUtils.SPLIT_LINE, strings));
            if (keyword.children == null) {
                keyword.setChildrenFromDescription();
                keyword.children.removeIf(tip -> tip == power.tooltip);
            }

        }
    }

    @Override
    public void updateImpl() {
        super.updateImpl();
        elementImage.setTargetColor(canIntensify() ? Color.WHITE : Color.GRAY).updateImpl();
        if (hb.hovered) {
            updateDescription();
            GameUtilities.highlightMatchingCards(power.affinity);
        }
        for (ConjurerReactionButton button : reactions.values()) {
            button.tryUpdate();
        }

        intensifyFontScale = PCLRenderHelpers.lerpScale(intensifyFontScale, BASE_AMOUNT_SCALE);
    }

    public void updatePreview(AffinityReactions afs) {
        unsetPreview();

        if (afs.hasReaction(power.affinity)) {
            for (PCLAffinity reactor : afs.reactions.get(power.affinity).keySet()) {
                ConjurerReactionButton button = reactions.get(reactor);
                if (button != null) {
                    button.highlight();
                }
            }
        }
    }

    public boolean willReact(AbstractCreature m) {
        return m.hasPower(power.ID) || EUIUtils.any(additionalPowers, m::hasPower);
    }
}

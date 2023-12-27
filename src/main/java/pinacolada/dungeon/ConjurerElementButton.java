package pinacolada.dungeon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import extendedui.EUIRenderHelpers;
import extendedui.EUIUtils;
import extendedui.ui.controls.EUIButton;
import extendedui.ui.controls.EUIImage;
import extendedui.ui.hitboxes.EUIHitbox;
import extendedui.ui.tooltips.EUIKeywordTooltip;
import extendedui.utilities.EUIColors;
import org.apache.commons.lang3.StringUtils;
import pinacolada.actions.PCLActions;
import pinacolada.actions.cards.TryChooseChoice;
import pinacolada.cards.base.ChoiceCard;
import pinacolada.cards.base.ChoiceCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardAffinity;
import pinacolada.effects.PCLEffects;
import pinacolada.effects.affinity.GenericFlashEffect;
import pinacolada.interfaces.subscribers.OnTryElementReactSubscriber;
import pinacolada.powers.conjurer.ElementPowerData;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerImages;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.utilities.GameUtilities;
import pinacolada.utilities.PCLRenderHelpers;

import java.util.*;
import java.util.stream.Collectors;

import static pinacolada.skills.PSkill.COLON_SEPARATOR;

public class ConjurerElementButton extends EUIButton {
    public static final Color ACTIVE_COLOR = new Color(0.5f, 1f, 0.5f, 1f);
    public static final int BASE_COST = 8;
    public static final int BASE_COST_RATE = 4;
    public static final int BASE_CHOICES = 3;
    public static final float BASE_AMOUNT_SCALE = 1f;
    protected final HashMap<PCLAffinity, ConjurerReactionGroup> reactions = new HashMap<>();
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

    public ConjurerElementButton(ConjurerReactionMeter meter, ElementPowerData power, Texture texture, EUIHitbox hb) {
        super(ConjurerImages.Core.squareBg.texture(), hb);
        this.meter = meter;
        this.power = power;
        elementImage = new EUIImage(texture, hb).setColor(Color.GRAY).setScale(0.625f, 0.625f);
        keyword = new EUIKeywordTooltip(power.strings.NAME);

        setOnClick(this::manualAddLevel);
        setTooltip(keyword);
    }

    protected static String getEffectsString(HashMap<PCLAffinity, ConjurerReactionGroup> reactions) {
        return EUIUtils.joinStringsMap(EUIUtils.SPLIT_LINE, af -> {
            return af.getKey().getFormattedAffinitySymbol() + COLON_SEPARATOR + af.getValue().getSkillsText();
        }, reactions.entrySet().stream().sorted((a,b) -> a.getKey().compareTo(b.getKey())).collect(Collectors.toList()));
    }

    public void addAdditionalPower(String powerID) {
        this.additionalPowers.add(powerID);
    }

    public void addLevel(int amount) {
        level += amount;
        currentCost += level * BASE_COST_RATE * currentCostMultiplier;
        for (int i = 0; i < amount; i++) {
            PCLActions.bottom.callback(this::chooseEffects);
        }
    }

    public void addReaction(PCLAffinity af, PSkill<?>... skills) {
        ConjurerReactionGroup group = reactions.get(af);
        if (group != null) {
            group.addSkills(Arrays.asList(skills));
        }
        else {
            ConjurerReactionMeter.meter.addReaction(this, ConjurerReactionMeter.meter.getElementButton(af), skills);
        }
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

    protected void chooseEffects() {
        ArrayList<ChoiceCardData<HashMap<PCLAffinity, ConjurerReactionGroup>>> cards = new ArrayList<>();
        for (int i = 0; i < BASE_CHOICES; i++) {
            int j = 0;
            HashMap<PCLAffinity, ConjurerReactionGroup> newGroups = new HashMap<>();
            for (Map.Entry<PCLAffinity, ConjurerReactionGroup> gr : reactions.entrySet()) {
                ConjurerReactionGroup upgrade = gr.getValue().createCopyWithUpgrade(i == j);
                newGroups.put(gr.getKey(), upgrade);
                j++;
            }

            ChoiceCardData<HashMap<PCLAffinity, ConjurerReactionGroup>> data = ChoiceCardData.affinity(power.affinity, newGroups);
            data.setName(power.getName());
            data.setDescription(getEffectsString(newGroups));
            cards.add(data);
        }
        PCLActions.top.add(new TryChooseChoice<>(AbstractGameAction.ActionType.SPECIAL, power.getName(), AbstractDungeon.player, 1, -2, cards))
                .setMessage(EUIUtils.EMPTY_STRING)
                .setDynamicMessage(__ -> EUIUtils.format(ConjurerResources.conjurer.strings.combat_conjurerMeterReact, power.getTooltip().getTitleOrIcon()))
                .addCallback(choices -> {
                    for (ChoiceCard<HashMap<PCLAffinity, ConjurerReactionGroup>> choice : choices) {
                        reactions.clear();
                        reactions.putAll(choice.value);
                    }
                });
    }

    public void flash() {
        intensifyFontScale = 8.0f;
        //ElementImage.SetColor(Color.WHITE);
        PCLEffects.List.add(new GenericFlashEffect(elementImage.texture, this.hb.cX, this.hb.cY, true).setScale(Settings.scale * 0.5f));
    }

    public float getCurrentCostMultiplier() {
        return currentCostMultiplier;
    }

    public String getEffectsString() {
        return getEffectsString(reactions);
    }

    public String getEffectsString(Collection<PCLAffinity> affinities) {
        return EUIUtils.joinStringsMapNonnull(EUIUtils.SPLIT_LINE, af -> {
            ConjurerReactionGroup group = reactions.get(af);
            return group != null ? af.getFormattedAffinitySymbol() + COLON_SEPARATOR + group.getSkillsText() : null;
        }, affinities);
    }

    public Set<PCLAffinity> getReactAffinities() {
        return new HashSet<>(reactions.keySet());
    }

    public List<PSkill<?>> getReactEffects(PCLAffinity affinity) {
        return reactions.get(affinity).getSkills();
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
        return CombatManager.subscriberInout(OnTryElementReactSubscriber.class, cAff.level, (s, d) -> s.onTryElementReact(d, power.affinity, cAff.type, po));
    }

    public void renderForTutorial(SpriteBatch sb, float x, float y) {
        super.renderImpl(sb);
        elementImage.renderCentered(sb, x, y, hb.width, hb.height);
    }

    @Override
    public void renderImpl(SpriteBatch sb) {
        super.renderImpl(sb);
        PCLRenderHelpers.drawGrayscaleIf(sb, s -> elementImage.renderCentered(s, hb), busy || !canInteract);
        FontHelper.renderFontCentered(sb, FontHelper.powerAmountFont, "L" + level, hb.cX + scale(15), hb.cY - scale(15), EUIColors.blue(1f), intensifyFontScale);
    }

    public void setCurrentCostMultiplier(float mult) {
        currentCost *= mult / currentCostMultiplier;
        currentCostMultiplier = mult;
    }

    public void setEnabled(boolean value) {
        reactionEnabled = value;
        elementImage.setShaderMode(reactionEnabled ? EUIRenderHelpers.ShaderMode.Normal : EUIRenderHelpers.ShaderMode.Grayscale);
    }

    public void setLevelEnabled(boolean value) {
        canInteract = value;
    }

    public void setReaction(PCLAffinity af, PSkill<?>... skills) {
        ConjurerReactionGroup group = reactions.get(af);
        if (group != null) {
            group.setSkills(Arrays.asList(skills));
        }
        else {
            ConjurerReactionMeter.meter.addReaction(this, ConjurerReactionMeter.meter.getElementButton(af), skills);
        }
    }

    public void tryAddLevel() {
        if (meter.trySpendMatter(currentCost)) {
            addLevel(1);
            PCLEffects.Queue.add(new GenericFlashEffect(elementImage.texture, this.hb.cX, this.hb.cY, true).setScale(Settings.scale * 0.5f));
            CombatManager.onIncreaseAffinityLevel(power.affinity);
        }
    }

    public void unsetPreview() {
        // TODO unhighlight the button itself
    }

    public void updateDescription() {
        if (PGR.isLoaded()) {
            ArrayList<String> strings = new ArrayList<>();
            strings.add(EUIUtils.format(ConjurerResources.conjurer.strings.combat_conjurerMeterBonus, power.getTooltip()));
            strings.add(getEffectsString());
            if (canIntensify()) {
                strings.add(EUIUtils.SPLIT_LINE + EUIUtils.format(ConjurerResources.conjurer.strings.combat_conjurerMeterNextIntensity, currentCost, power));
            }
            //strings.add(PCLCoreStrings.headerString(ConjurerResources.conjurer.strings.combat_conjurerMeterCost, currentCost));

            keyword.setIcon(power.getTooltip().icon);
            keyword.setDescription(EUIUtils.joinStrings(EUIUtils.SPLIT_LINE, strings));
            if (keyword.children == null) {
                keyword.setChildrenFromDescription(false);
                keyword.children.removeIf(tip -> tip == power.tooltip || EUIUtils.any(PCLAffinity.basic(), af -> tip == af.tooltip));
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

        intensifyFontScale = PCLRenderHelpers.lerpScale(intensifyFontScale, BASE_AMOUNT_SCALE);
    }

    protected void updatePreview(AffinityReactions afs, StringJoiner sj) {
        unsetPreview();

        if (afs.hasReaction(power.affinity)) {
           for (HashMap<PCLAffinity, Integer> targetMap : afs.reactions.get(power.affinity).values()) {
               String res = getEffectsString(targetMap.keySet());
               if (!StringUtils.isEmpty(res)) {
                   sj.add(res);
               }
            }
        }
    }

    public boolean willReact(AbstractCreature m) {
        return m.hasPower(power.ID) || EUIUtils.any(additionalPowers, m::hasPower);
    }
}

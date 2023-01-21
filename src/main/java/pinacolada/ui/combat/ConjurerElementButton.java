package pinacolada.ui.combat;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import extendedui.EUIRenderHelpers;
import extendedui.EUIUtils;
import extendedui.ui.controls.EUIButton;
import extendedui.ui.controls.EUIImage;
import extendedui.ui.hitboxes.EUIHitbox;
import extendedui.ui.tooltips.EUITooltip;
import extendedui.utilities.EUIColors;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCardAffinity;
import pinacolada.effects.PCLEffects;
import pinacolada.effects.affinity.GenericFlashEffect;
import pinacolada.interfaces.subscribers.OnTryElementReactSubscriber;
import pinacolada.misc.CombatManager;
import pinacolada.powers.conjurer.AbstractPCLElementalPower;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.resources.pcl.PCLCoreStrings;
import pinacolada.utilities.GameUtilities;
import pinacolada.utilities.PCLRenderHelpers;

import java.util.*;

public class ConjurerElementButton extends EUIButton
{
    public static final String INCREASE_ID = ConjurerResources.conjurer.createID(ConjurerElementButton.class.getSimpleName());
    public static final Color ACTIVE_COLOR = new Color(0.5f, 1f, 0.5f, 1f);
    public static final int BASE_COST = 10;
    public static final int BASE_COST_RATE = 5;
    public static final float BASE_AMOUNT_SCALE = 1f;

    public final PCLAffinity affinity;
    public final ArrayList<String> additionalPowers = new ArrayList<>();
    public final ConjurerReactionMeter meter;
    protected final HashMap<PCLAffinity, ConjurerReactionButton> reactions = new HashMap<>();
    protected final PowerStrings reactionStrings;
    protected EUIImage elementImage;
    protected int currentCost;
    protected int level;
    protected float intensifyFontScale = BASE_AMOUNT_SCALE;
    protected boolean reactionEnabled;

    public ConjurerElementButton(ConjurerReactionMeter meter, PCLAffinity affinity, Texture texture, EUIHitbox hb)
    {
        super(PGR.core.images.core.squareBG2.texture(), hb);
        this.meter = meter;
        this.affinity = affinity;
        reactionStrings = PGR.getPowerStrings(elementID());
        elementImage = new EUIImage(texture, hb).setColor(Color.GRAY).setScale(0.5f, 0.5f);

        setOnClick(this::manualAddLevel);
        setTooltip(reactionStrings.NAME, "");
    }

    public void addAdditionalPower(String powerID)
    {
        this.additionalPowers.add(powerID);
    }

    public ConjurerElementButton addCombustion(ConjurerElementButton reactor)
    {
        reactions.put(reactor.affinity, new ConjurerReactionButton(this, reactor, Type.Combust));
        return this;
    }

    public void addLevel(int amount)
    {
        level += amount;
        currentCost += level * BASE_COST_RATE;
    }

    public ConjurerElementButton addRedox(ConjurerElementButton reactor)
    {
        reactions.put(reactor.affinity, new ConjurerReactionButton(this, reactor, Type.Redox));
        return this;
    }

    protected boolean canCombust(PCLAffinity affinity, AbstractCreature m)
    {
        return hasCombust(affinity) && m != null && willReact(m);
    }

    protected boolean canCombust(PCLAffinity affinity, String powerID)
    {
        return hasCombust(affinity) && matchesPower(powerID);
    }

    public boolean canIntensify()
    {
        return meter.getReactionCount() >= currentCost;
    }

    public boolean canReact(Type type, PCLAffinity affinity, AbstractCreature m)
    {
        return reactionEnabled && type == Type.Combust ? canCombust(affinity, m) : canRedox(affinity, m);
    }

    public boolean canReact(Type type, PCLAffinity affinity, String powerID)
    {
        return reactionEnabled && type == Type.Combust ? canCombust(affinity, powerID) : canRedox(affinity, powerID);
    }

    protected boolean canRedox(PCLAffinity affinity, AbstractCreature m)
    {
        return hasRedox(affinity) && m != null && willReact(m);
    }

    protected boolean canRedox(PCLAffinity affinity, String powerID)
    {
        return hasRedox(affinity) && matchesPower(powerID);
    }

    public String elementID()
    {
        return elementPower().ID;
    }

    public PCLElementHelper elementPower()
    {
        return PCLElementHelper.get(affinity);
    }

    public void flash()
    {
        intensifyFontScale = 8.0f;
        //ElementImage.SetColor(Color.WHITE);
        PCLEffects.List.add(new GenericFlashEffect(elementImage.texture, this.hb.cX, this.hb.cY, true).setScale(Settings.scale * 0.5f));
    }

    public Set<PCLAffinity> getCombustAffinities()
    {
        Set<PCLAffinity> affinities = new HashSet<>();
        for (ConjurerReactionButton button : reactions.values())
        {
            if (button.type == Type.Combust)
            {
                affinities.add(button.source.affinity);
            }
        }
        return affinities;
    }

    public Set<PCLAffinity> getRedoxAffinities()
    {
        Set<PCLAffinity> affinities = new HashSet<>();
        for (ConjurerReactionButton button : reactions.values())
        {
            if (button.type == Type.Redox)
            {
                affinities.add(button.source.affinity);
            }
        }
        return affinities;
    }

    public boolean hasReaction(PCLAffinity affinity)
    {
        return affinity == PCLAffinity.Star || reactions.containsKey(affinity);
    }

    public boolean hasCombust(PCLAffinity affinity)
    {
        return affinity == PCLAffinity.Star || (hasReaction(affinity) && reactions.get(affinity).type == Type.Combust);
    }

    public boolean hasRedox(PCLAffinity affinity)
    {
        return affinity == PCLAffinity.Star || (hasReaction(affinity) && reactions.get(affinity).type == Type.Redox);
    }

    public void initialize()
    {
        setEnabled(true);
        additionalPowers.clear();
        reactions.clear();
        level = 0;
        currentCost = BASE_COST;
        elementImage.setColor(Color.GRAY);
    }

    public void manualAddLevel()
    {
        meter.set(affinity, 0);
        if (CombatManager.tryActivateSemiLimited(INCREASE_ID))
        {
            meter.addSkip(1);
        }
        tryAddLevel();
    }

    public int reactionGain(AbstractPower po, PCLCardAffinity cAff, Type type)
    {
        return CombatManager.subscriberInout(OnTryElementReactSubscriber.class, po.amount * cAff.level, (s, d) -> s.onTryElementReact(d, affinity, cAff.type));
    }

    public void setEnabled(boolean value)
    {
        reactionEnabled = value;
        elementImage.setShaderMode(reactionEnabled ? EUIRenderHelpers.ShaderMode.Normal : EUIRenderHelpers.ShaderMode.Grayscale);
        for (ConjurerReactionButton button : reactions.values())
        {
            button.setActive(reactionEnabled);
        }
    }

    public void tryAddLevel()
    {
        if (meter.trySpendCount(currentCost))
        {
            addLevel(1);
            PCLEffects.List.add(new GenericFlashEffect(elementImage.texture, this.hb.x, this.hb.y, true).setScale(Settings.scale * 0.5f));
            CombatManager.onIncreaseAffinityLevel(affinity);
        }
    }

    public void unsetPreview()
    {
        for (ConjurerReactionButton button : reactions.values())
        {
            if (!button.hb.hovered)
            {
                button.unhighlight();
            }
        }
    }

    @Override
    public void updateImpl()
    {
        super.updateImpl();
        elementImage.setTargetColor(canIntensify() ? Color.WHITE : Color.GRAY).updateImpl();
        if (hb.hovered)
        {
            updateDescription();
            GameUtilities.highlightMatchingCards(affinity);
        }
        for (ConjurerReactionButton button : reactions.values())
        {
            button.tryUpdate();
        }

        intensifyFontScale = PCLRenderHelpers.lerpScale(intensifyFontScale, BASE_AMOUNT_SCALE);
    }

    @Override
    public void renderImpl(SpriteBatch sb)
    {
        super.renderImpl(sb);
        elementImage.renderCentered(sb, hb);
        for (ConjurerReactionButton button : reactions.values())
        {
            button.tryRenderCentered(sb);
        }

        FontHelper.renderFontCentered(sb, FontHelper.powerAmountFont, "L" + level, hb.cX + scale(15), hb.cY - scale(15), EUIColors.blue(1f), intensifyFontScale);
    }

    public void updateDescription()
    {
        if (PGR.isLoaded())
        {
            PCLElementHelper power = elementPower();

            ArrayList<String> strings = new ArrayList<>();
            strings.add(PCLCoreStrings.headerString(PGR.core.tooltips.level.title, level));
            strings.add(PCLCoreStrings.headerString(PGR.core.strings.combat.conjurerMeterCost, currentCost));
            strings.add(PGR.core.strings.combat.effect(EUIUtils.format(reactionStrings.DESCRIPTIONS[1], PCLRenderHelpers.decimalFormat(AbstractPCLElementalPower.getIntensifyMultiplier(elementID())))
                    + " " + EUIUtils.format(PGR.core.strings.combat.conjurerMeterDamage, affinity.getTooltip(), PCLRenderHelpers.decimalFormat(AbstractPCLElementalPower.getAmplifyMultiplier(affinity)))));

            if (canIntensify())
            {
                strings.add(EUIUtils.SPLIT_LINE + EUIUtils.format(PGR.core.strings.combat.conjurerMeterNextIntensity, currentCost, power));
            }

            tooltip.setIcon(power.getTooltip().icon);
            tooltip.setDescription(EUIUtils.joinStrings(EUIUtils.SPLIT_LINE, strings));
            if (tooltip.child == null)
            {
                tooltip.child = new EUITooltip(PGR.core.strings.combat.nextLevelEffect);
            }
            tooltip.child.setDescription(EUIUtils.joinStrings(EUIUtils.SPLIT_LINE,
                    PCLCoreStrings.headerString(PGR.core.tooltips.level.title, level + 1),
                    PGR.core.strings.combat.effect(EUIUtils.format(reactionStrings.DESCRIPTIONS[1], PCLRenderHelpers.decimalFormat(AbstractPCLElementalPower.getIntensifyMultiplier(elementID(), level + 1)))
                    + " " + EUIUtils.format(PGR.core.strings.combat.conjurerMeterDamage, affinity.getTooltip(), PCLRenderHelpers.decimalFormat(AbstractPCLElementalPower.getAmplifyMultiplier(affinity, level + 1))))
            ));
        }
    }

    public int updatePreview(List<PCLCardAffinity> lastCardAffinities, List<AbstractPCLElementalPower> lastTargetPowers)
    {
        int retAmount = 0;
        unsetPreview();

        for (AbstractPower po : lastTargetPowers)
        {
            if (matchesPower(po.ID))
            {
                for (PCLCardAffinity af : lastCardAffinities)
                {
                    if (hasReaction(af.type))
                    {
                        if (af.type == PCLAffinity.Star)
                        {
                            for (ConjurerReactionButton reaction : reactions.values())
                            {
                                reaction.highlight();
                                retAmount += reactionGain(po, af, reaction.type);
                            }
                        }
                        else
                        {
                            ConjurerReactionButton reaction = reactions.get(af.type);
                            reaction.highlight();
                            retAmount += reactionGain(po, af, reaction.type);
                        }
                    }
                }
            }
        }
        return retAmount;
    }

    public boolean matchesPower(String id) {return elementID().equals(id) || EUIUtils.any(additionalPowers, s -> s.equals(id)); }

    public boolean willReact(AbstractCreature m)
    {
        return m.hasPower(elementID()) || EUIUtils.any(additionalPowers, m::hasPower);
    }

    public enum Type
    {
        Combust,
        Redox
    }
}

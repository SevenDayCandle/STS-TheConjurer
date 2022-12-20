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
import extendedui.ui.controls.EUILabel;
import extendedui.ui.controls.EUITextBox;
import extendedui.ui.hitboxes.PercentageRelativeHitbox;
import extendedui.utilities.EUIColors;
import extendedui.utilities.EUIFontHelper;
import pinacolada.actions.PCLActions;
import pinacolada.actions.powers.ElementReaction;
import pinacolada.cards.base.*;
import pinacolada.orbs.PCLOrb;
import pinacolada.powers.conjurer.AbstractPCLElementalPower;
import pinacolada.powers.conjurer.FrozenPower;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.GameUtilities;
import pinacolada.utilities.PCLRenderHelpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static extendedui.EUIUtils.array;

public class ConjurerReactionMeter extends PCLPlayerMeter
{
    public static final Color ACTIVE_COLOR = new Color(0.5f, 1f, 0.5f, 1f);
    public static final float ICON_SIZE = scale(48);
    public static final float DISTANCE = 2f;
    public static final float BASE_AMOUNT_SCALE = 1f;
    public static final int BASE_MORPH = 1;
    public static final ConjurerReactionMeter meter = new ConjurerReactionMeter();

    protected ArrayList<ConjurerElementButton> elements = new ArrayList<>();
    protected ConjurerElementButton fire;
    protected ConjurerElementButton air;
    protected ConjurerElementButton water;
    protected ConjurerElementButton earth;
    protected ConjurerElementButton light;
    protected ConjurerElementButton dark;
    protected EUILabel morphHeader;
    protected EUILabel reactionHeader;
    protected EUITextBox morphCountText;
    protected EUITextBox reactionCountText;
    protected int reactionCount;
    protected int morphs = BASE_MORPH;
    protected int baseMorphs = morphs;
    private PCLAffinity lastReaction = PCLAffinity.General;
    private List<PCLCardAffinity> lastCardAffinities = new ArrayList<>();
    private List<AbstractPCLElementalPower> lastTargetPowers = new ArrayList<>();
    private int reactionPreview;


    public ConjurerReactionMeter()
    {
        super(ConjurerResources.conjurer.config.meterPosition, ICON_SIZE);

        fire = new ConjurerElementButton(this, PCLAffinity.Red, PGR.core.images.core.elementFire.texture(), new PercentageRelativeHitbox(hb, 2, 2, 5f, -1 + DISTANCE));
        air = new ConjurerElementButton(this, PCLAffinity.Green,PGR.core.images.core.elementAir.texture(), new PercentageRelativeHitbox(hb, 2, 2, 5f, -1 - DISTANCE));
        water = new ConjurerElementButton(this, PCLAffinity.Blue, PGR.core.images.core.elementWater.texture(), new PercentageRelativeHitbox(hb, 2, 2, 5f + DISTANCE, -1));
        earth = new ConjurerElementButton(this, PCLAffinity.Orange, PGR.core.images.core.elementEarth.texture(), new PercentageRelativeHitbox(hb, 2, 2, 5f - DISTANCE, -1));
        light = new ConjurerElementButton(this, PCLAffinity.Light, PGR.core.images.core.elementLight.texture(), new PercentageRelativeHitbox(hb, 2, 2, 5f, -1));
        dark = new ConjurerElementButton(this, PCLAffinity.Dark, PGR.core.images.core.elementDark.texture(), new PercentageRelativeHitbox(hb, 2, 2, 5f, -1));
        elements.add(fire);
        elements.add(air);
        elements.add(water);
        elements.add(earth);
        elements.add(light);
        elements.add(dark);

        // Light/Dark are hidden
        light.setActive(false);
        dark.setActive(false);

        reactionHeader = (EUILabel) new EUILabel(EUIFontHelper.cardtitlefontSmall,
                new PercentageRelativeHitbox(hb, 2, 2, 10f, 0.6f)).setLabel(ConjurerResources.conjurer.tooltips.reaction.title)
                .setFontScale(0.85f)
                .setAlignment(0.85f, 0.5f)
                .setTooltip(ConjurerResources.conjurer.tooltips.reaction);
        reactionCountText = new EUITextBox(EUIRM.images.panelEllipticalHalfH.texture(), new PercentageRelativeHitbox(hb, 2, 1.8f, 10f, 0.17f))
                .setColors(EUIColors.black(0.6f), Settings.CREAM_COLOR)
                .setAlignment(0.5f, 0.5f)
                .setFont(EUIFontHelper.cardtitlefontNormal, BASE_AMOUNT_SCALE);

        morphHeader = (EUILabel) new EUILabel(EUIFontHelper.cardtitlefontSmall,
                new PercentageRelativeHitbox(hb, 2, 2, 10f, -1.92f)).setLabel(ConjurerResources.conjurer.tooltips.morph.title)
                .setFontScale(0.75f)
                .setAlignment(0.85f, 0.5f)
                .setTooltip(ConjurerResources.conjurer.tooltips.morph);
        morphCountText = new EUITextBox(EUIRM.images.panelEllipticalHalfH.texture(), new PercentageRelativeHitbox(hb, 1.6f, 1.3f, 10f, -2.1f))
                .setColors(EUIColors.black(0.6f), Settings.CREAM_COLOR)
                .setAlignment(0.5f, 0.5f)
                .setFont(EUIFontHelper.cardtitlefontNormal, 0.6f);
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
        baseMorphs += 1;
        morphs = baseMorphs;
    }

    public void disableAffinity(PCLAffinity affinity)
    {
        ConjurerElementButton button = getElementButton(affinity);
        if (button != null)
        {
            button.setEnabled(false);
        }
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
        return lastReaction;
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
        morphCountText.tryRender(sb);
        morphHeader.tryRender(sb);
    }

    public void initialize()
    {
        super.initialize();
        for (ConjurerElementButton b : elements)
        {
            b.initialize();
        }
        reactionPreview = reactionCount = 0;
        baseMorphs = morphs = BASE_MORPH;
        lastReaction = PCLAffinity.General;
        lastCardAffinities = new ArrayList<>();
        lastTargetPowers = new ArrayList<>();

        addDefaultReactions();

        water.addAdditionalPower(FrozenPower.POWER_ID);
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
        reactionCountText.setLabel(reactionPreview).setFontColor(reactionPreview != reactionCount ? ACTIVE_COLOR : EUIColors.blue(1f)).tryUpdate();
        morphHeader.tryUpdate();
        morphCountText.setLabel(morphs + "/" + baseMorphs).setFontColor(morphs > 0 ? Settings.GREEN_TEXT_COLOR : EUIColors.cream(0.6f)).tryUpdate();
    }

    @Override
    public void onCardPlayed(AbstractCard card, AbstractCreature m, PCLUseInfo info)
    {
        if (info != null && !info.reactions.isEmpty())
        {
            PCLActions.last.add(new ElementReaction(info.reactions, card, m));
        }
    }

    @Override
    public PCLAffinity set(PCLAffinity affinity, int target)
    {
        lastReaction = affinity;
        return getCurrentAffinity();
    }

    public void onStartOfTurn()
    {
        morphs = baseMorphs;
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

    public int getMorphCount() {return morphs;}

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

    public boolean tryUseMorph(PCLAffinity dest, PCLAffinity target)
    {
        ConjurerElementButton destButton = getElementButton(dest);
        return destButton != null && tryUseMorph(destButton.reactions.get(target));
    }

    public boolean tryUseMorph(ConjurerReactionButton button)
    {
        if (morphs > 0 && button != null)
        {
            button.morphAction();
            morphs -= 1;
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

}

package pinacolada.dungeon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.*;
import com.megacrit.cardcrawl.cards.green.*;
import com.megacrit.cardcrawl.cards.purple.*;
import com.megacrit.cardcrawl.cards.red.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import extendedui.EUIRM;
import extendedui.EUIUtils;
import extendedui.ui.controls.EUILabel;
import extendedui.ui.controls.EUITextBox;
import extendedui.ui.controls.EUITutorialImagePage;
import extendedui.ui.controls.EUITutorialPage;
import extendedui.ui.hitboxes.RelativeHitbox;
import extendedui.ui.tooltips.EUIKeywordTooltip;
import extendedui.utilities.EUIColors;
import extendedui.utilities.EUIFontHelper;
import pinacolada.actions.PCLActions;
import pinacolada.actions.powers.ElementReaction;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardAffinities;
import pinacolada.cards.base.fields.PCLCardAffinity;
import pinacolada.characters.ConjurerCharacter;
import pinacolada.monsters.PCLCardAlly;
import pinacolada.orbs.PCLOrb;
import pinacolada.powers.conjurer.*;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerImages;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.GameUtilities;
import pinacolada.utilities.PCLRenderHelpers;

import java.util.*;

import static extendedui.EUIUtils.array;

public class ConjurerReactionMeter extends PCLPlayerMeter {
    private static final HashMap<String, Set<PCLAffinity>> CARD_AFFINITIES = new HashMap<>();
    public static final String ID = createFullID(ConjurerResources.conjurer, ConjurerReactionMeter.class);
    public static final Color ACTIVE_COLOR = new Color(0.5f, 1f, 0.5f, 1f);
    public static final float ICON_SIZE = scale(48);
    public static final float BASE_AMOUNT_SCALE = 1f;
    public static final float BASE_CHARGE_SCALE = 0.6f;
    public static final float BUTTON_SCALE = 1.5f;
    public static final float OFFSET_SCALE_X = 2.5f * BUTTON_SCALE;
    public static final float OFFSET_SCALE_Y = -0.5f * BUTTON_SCALE;
    public static final ConjurerReactionMeter meter = new ConjurerReactionMeter();
    private AffinityReactions previewReactions;
    private int matterPreview;
    protected ArrayList<ConjurerElementButton> elements = new ArrayList<>();
    protected ConjurerElementButton fire;
    protected ConjurerElementButton water;
    protected ConjurerElementButton air;
    protected ConjurerElementButton earth;
    protected ConjurerElementButton light;
    protected ConjurerElementButton dark;
    protected EUILabel reactionHeader;
    protected EUITextBox reactionCountText;
    protected int matterCount;
    protected int totalReactions;
    protected PCLAffinity lastUpgrade = PCLAffinity.General;


    public ConjurerReactionMeter() {
        super(ID, ConjurerResources.conjurer.data.config.meterPosition, ICON_SIZE);

        fire = new ConjurerElementButton(this, IgnisPower.DATA, ConjurerImages.Core.elementFire.texture(), RelativeHitbox.fromPercentages(hb, BUTTON_SCALE, BUTTON_SCALE, OFFSET_SCALE_X, OFFSET_SCALE_Y + BUTTON_SCALE));
        water = new ConjurerElementButton(this, AquaPower.DATA, ConjurerImages.Core.elementWater.texture(), RelativeHitbox.fromPercentages(hb, BUTTON_SCALE, BUTTON_SCALE, OFFSET_SCALE_X + BUTTON_SCALE, OFFSET_SCALE_Y));
        air = new ConjurerElementButton(this, VentusPower.DATA, ConjurerImages.Core.elementAir.texture(), RelativeHitbox.fromPercentages(hb, BUTTON_SCALE, BUTTON_SCALE, OFFSET_SCALE_X, OFFSET_SCALE_Y - BUTTON_SCALE));
        earth = new ConjurerElementButton(this, PetraPower.DATA, ConjurerImages.Core.elementEarth.texture(), RelativeHitbox.fromPercentages(hb, BUTTON_SCALE, BUTTON_SCALE, OFFSET_SCALE_X - BUTTON_SCALE, OFFSET_SCALE_Y));
        light = new ConjurerElementButton(this, LuxPower.DATA, ConjurerImages.Core.elementLight.texture(), RelativeHitbox.fromPercentages(hb, BUTTON_SCALE, BUTTON_SCALE, OFFSET_SCALE_X, OFFSET_SCALE_Y));
        dark = new ConjurerElementButton(this, UmbraPower.DATA, ConjurerImages.Core.elementDark.texture(), RelativeHitbox.fromPercentages(hb, BUTTON_SCALE, BUTTON_SCALE, OFFSET_SCALE_X, OFFSET_SCALE_Y));
        elements.add(fire);
        elements.add(water);
        elements.add(air);
        elements.add(earth);
        elements.add(light);
        elements.add(dark);

        // Light/Dark are hidden
        light.setActive(false);
        dark.setActive(false);

        reactionHeader = new EUILabel(EUIFontHelper.cardTitleFontSmall,
                RelativeHitbox.fromPercentages(hb, 2, 2, 8f, 0.1f)).setLabel(ConjurerResources.conjurer.tooltips.matter.title)
                .setFontScale(0.8f)
                .setAlignment(0.85f, 0.5f)
                .setTooltip(ConjurerResources.conjurer.tooltips.matter);
        reactionCountText = new EUITextBox(EUIRM.images.panelEllipticalHalfH.texture(), RelativeHitbox.fromPercentages(hb, 2, 1.8f, 8f, -0.47f))
                .setColors(EUIColors.black(0.6f), Settings.CREAM_COLOR)
                .setAlignment(0.5f, 0.5f)
                .setFont(EUIFontHelper.cardTitleFontNormal, BASE_AMOUNT_SCALE);
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

    public void applyAffinities(AbstractCard card) {
        for (PCLAffinity af : getAffinitiesForNonPCL(card)) {
            GameUtilities.modifyAffinityLevel(card, af, 1, true);
        }
    }

    public void disableAffinity(PCLAffinity affinity) {
        ConjurerElementButton button = getElementButton(affinity);
        if (button != null) {
            button.setEnabled(false);
        }
    }

    protected void fillReactions(AffinityReactions reactions, ArrayList<PCLCardAffinity> affs, Collection<? extends AbstractCreature> mo) {
        for (AbstractCreature m : mo) {
            if (m.powers != null) {
                for (AbstractPower po : m.powers) {
                    for (ConjurerElementButton button : getElementButtons()) {
                        if (button.matchesPower(po.ID)) {
                            for (PCLCardAffinity af : affs) {
                                if (button.hasReact(af.type)) {
                                    reactions.addReaction(button.power.affinity, af.type, button.reactionGain(po, af));
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

    @Override
    public PCLAffinity get(int target) {
        return lastUpgrade;
    }

    public Set<PCLAffinity> getAffinitiesForNonPCL(AbstractCard card) {
        switch (card.cardID) {
            case AllOutAttack.ID:
            case Anger.ID:
            case Bludgeon.ID:
            case Combust.ID:
            case Eruption.ID:
            case Flex.ID:
            case Immolate.ID:
            case Inflame.ID:
            case LimitBreak.ID:
            case Rage.ID:
            case RecklessCharge.ID:
            case Rushdown.ID:
            case Tantrum.ID:
            case Warcry.ID:
            case WreathOfFlame.ID:
                return EUIUtils.set(PCLAffinity.Red);
            case Accuracy.ID:
            case Acrobatics.ID:
            case Backflip.ID:
            case Backstab.ID:
            case Blur.ID:
            case BouncingFlask.ID:
            case BulletTime.ID:
            case Burst.ID:
            case DaggerSpray.ID:
            case DaggerThrow.ID:
            case Dash.ID:
            case EscapePlan.ID:
            case Flechettes.ID:
            case FlyingKnee.ID:
            case Footwork.ID:
            case Outmaneuver.ID:
            case Overclock.ID:
            case QuickSlash.ID:
            case Shockwave.ID:
            case SneakyStrike.ID:
            case Tempest.ID:
            case Turbo.ID:
            case Whirlwind.ID:
                return EUIUtils.set(PCLAffinity.Green);
            case Blizzard.ID:
            case Chill.ID:
            case ColdSnap.ID:
            case Glacier.ID:
            case LikeWater.ID:
            case Study.ID:
            case Tranquility.ID:
                return EUIUtils.set(PCLAffinity.Blue);
            case Armaments.ID:
            case Barricade.ID:
            case BodySlam.ID:
            case Clothesline.ID:
            case Discipline.ID:
            case Entrench.ID:
            case Establishment.ID:
            case Headbutt.ID:
            case Impervious.ID:
            case Juggernaut.ID:
            case MeteorStrike.ID:
            case Protect.ID:
            case SandsOfTime.ID:
            case Sentinel.ID:
            case ShrugItOff.ID:
            case Survivor.ID:
                return EUIUtils.set(PCLAffinity.Orange);
            case DualWield.ID:
            case SwordBoomerang.ID:
                return EUIUtils.set(PCLAffinity.Red, PCLAffinity.Green);
            case Adrenaline.ID:
            case FeelNoPain.ID:
            case TrueGrit.ID:
                return EUIUtils.set(PCLAffinity.Red, PCLAffinity.Orange);
            case Carnage.ID:
            case DemonForm.ID:
                return EUIUtils.set(PCLAffinity.Red, PCLAffinity.Purple);
            case BallLightning.ID:
            case Brilliance.ID:
            case Consecrate.ID:
            case Electrodynamics.ID:
            case Nirvana.ID:
            case Pray.ID:
            case ReachHeaven.ID:
            case Sanctity.ID:
            case StaticDischarge.ID:
            case ThunderStrike.ID:
            case Wish.ID:
            case Worship.ID:
            case Zap.ID:
                return EUIUtils.set(PCLAffinity.Yellow);
            case Blasphemy.ID:
            case DarkEmbrace.ID:
            case Darkness.ID:
            case DoomAndGloom.ID:
            case EndlessAgony.ID:
            case GhostlyArmor.ID:
            case Exhume.ID:
            case Nightmare.ID:
            case Offering.ID:
            case PhantasmalKiller.ID:
            case PiercingWail.ID:
            case Reaper.ID:
            case SeverSoul.ID:
            case Terror.ID:
            case WraithForm.ID:
                return EUIUtils.set(PCLAffinity.Purple);
            case CreativeAI.ID:
            case DeusExMachina.ID:
            case FTL.ID:
            case MasterReality.ID:
            case Metallicize.ID:
            case SpotWeakness.ID:
                return EUIUtils.set(PCLAffinity.Silver);
            case Chaos.ID:
            case Rainbow.ID:
                return EUIUtils.set(PCLAffinity.Star);
        }
        return getAffinitiesFallbackForNonPCL(card);
    }

    // TODO use trie so we don't have to do a bajillion hardcoded string checks
    protected Set<PCLAffinity> getAffinitiesFallbackForNonPCL(AbstractCard card) {
        Set<PCLAffinity> affinities = CARD_AFFINITIES.get(card.cardID);
        if (affinities == null) {
            affinities = new HashSet<>();

            if (idHas(card, "Fire", "Flame", "Burn", "Scorch", "Heat", "Solar")) {
                affinities.add(PCLAffinity.Red);
            }
            if (idHas(card, "Lava", "Magma", "Volcan")) {
                affinities.add(PCLAffinity.Red);
                affinities.add(PCLAffinity.Orange);
            }
            if (idHas(card, "Water", "Ice", "Icicle", "Snow", "Frost", "Chill", "Cold", "Freeze", "Aqua", "Ocean", "Bubble", "Liquid")) {
                affinities.add(PCLAffinity.Blue);
            }
            if (idHas(card, "Storm", "Mist", "Fog", "Cloud")) {
                affinities.add(PCLAffinity.Blue);
                affinities.add(PCLAffinity.Green);
            }
            if (idHas(card, "Mud", "Swamp")) {
                affinities.add(PCLAffinity.Blue);
                affinities.add(PCLAffinity.Orange);
            }
            if (idHas(card, "Wind", "Sky", "Poison", "Toxic", "Air", "Smoke", "Breeze", "Tornado")) {
                affinities.add(PCLAffinity.Green);
            }
            if (idHas(card, "Leaf", "Nature", "Wood", "Forest", "Grass", "Blossom", "Bloom", "Plant", "Tree")) {
                affinities.add(PCLAffinity.Green);
                affinities.add(PCLAffinity.Orange);
            }
            if (idHas(card, "Earth", "Rock", "Stone", "Ground", "Land")) {
                affinities.add(PCLAffinity.Orange);
            }
            if (idHas(card, "Electric", "Thunder", "Lightning", "Shock", "Volt", "Holy", "Bless", "Sacred")) {
                affinities.add(PCLAffinity.Yellow);
            }
            if (idHas(card, "Dark", "Shadow", "Evil", "Night", "Curse", "Void", "Corrupt", "infinitespire")) {
                affinities.add(PCLAffinity.Purple);
            }
            if (idHas(card, "Metal", "Steel")) {
                affinities.add(PCLAffinity.Silver);
            }
            if (idHas(card, "Rainbow")) {
                affinities.add(PCLAffinity.Star);
            }

            CARD_AFFINITIES.put(card.cardID, affinities);
        }
        return affinities;
    }

    public int getAmplifyOffset(PCLAffinity affinity) {
        ConjurerElementButton destButton = getElementButton(affinity);
        return destButton != null ? destButton.currentAmplifyOffset : 0;
    }

    public ConjurerElementButton getElementButton(PCLAffinity affinity) {
        return affinity.ID >= 0 && affinity.ID < elements.size() ? elements.get(affinity.ID) : null;
    }

    public ArrayList<ConjurerElementButton> getElementButtons() {
        return elements;
    }

    private ArrayList<AbstractPCLElementalPower> getElementalPowers(AbstractCreature c) {
        return c != null && c.powers != null ? EUIUtils.mapAsNonnull(c.powers, po -> EUIUtils.safeCast(po, AbstractPCLElementalPower.class)) : new ArrayList<>();
    }

    @Override
    public String getInfoMainDescrption() {
        return ConjurerResources.conjurer.strings.conjurerSimple;
    }

    @Override
    public EUITutorialPage[] getInfoPages() {
        return array(
                new EUITutorialImagePage(makeTitle(PGR.core.strings.misc_fabricate, PGR.core.tooltips.affinityGeneral.title), PGR.core.strings.tutorial_affinityTutorial, ConjurerImages.Tutorial.afftut01.texture()),
                new EUITutorialImagePage(makeTitle(PGR.core.strings.misc_fabricate, PGR.core.tooltips.summon.title, 1), PGR.core.strings.tutorial_summonTutorial1, ConjurerImages.Tutorial.ctut01.texture()),
                new EUITutorialImagePage(makeTitle(PGR.core.strings.misc_fabricate, PGR.core.tooltips.summon.title, 2), PGR.core.strings.tutorial_summonTutorial2, ConjurerImages.Tutorial.ctut02.texture()),
                new EUITutorialImagePage(makeTitle(PGR.core.strings.misc_fabricate, PGR.core.tooltips.summon.title, 3), PGR.core.strings.tutorial_summonTutorial3, ConjurerImages.Tutorial.ctut03.texture()),
                new EUITutorialImagePage(makeTitle(PGR.core.strings.misc_fabricate, PGR.core.tooltips.summon.title, 4), PGR.core.strings.tutorial_summonTutorial4, ConjurerImages.Tutorial.ctut03.texture()),
                new EUITutorialImagePage(makeTitle(PGR.core.strings.misc_fabricate, PGR.core.tooltips.summon.title, 5), PGR.core.strings.tutorial_summonTutorial5, ConjurerImages.Tutorial.ctut03.texture()),
                new EUITutorialImagePage(makeTitle(PGR.core.strings.misc_fabricate, PGR.core.tooltips.summon.title, 6), PGR.core.strings.tutorial_summonTutorial6, ConjurerImages.Tutorial.ctut04.texture()),
                new EUITutorialImagePage(makeTitle(PGR.core.strings.misc_fabricate, PGR.core.tooltips.summon.title, 7), PGR.core.strings.tutorial_summonTutorial7, ConjurerImages.Tutorial.ctut05.texture()),
                new EUITutorialImagePage(makeTitle(PGR.core.strings.misc_fabricate, PGR.core.tooltips.summon.title, 8), PGR.core.strings.tutorial_summonTutorial8, ConjurerImages.Tutorial.ctut06.texture()),
                new EUITutorialImagePage(makeTitle(ConjurerCharacter.NAMES[0], ConjurerResources.conjurer.tooltips.element.title), ConjurerResources.conjurer.strings.conjurerTutorial1, ConjurerImages.Tutorial.etut01.texture()),
                new EUITutorialImagePage(makeTitle(ConjurerCharacter.NAMES[0], ConjurerResources.conjurer.tooltips.reaction.title, 1), ConjurerResources.conjurer.strings.conjurerTutorial2, ConjurerImages.Tutorial.etut02.texture()),
                new EUITutorialImagePage(makeTitle(ConjurerCharacter.NAMES[0], ConjurerResources.conjurer.tooltips.reaction.title, 2), ConjurerResources.conjurer.strings.conjurerTutorial3, ConjurerImages.Tutorial.etut02.texture()),
                new EUITutorialImagePage(makeTitle(ConjurerCharacter.NAMES[0], ConjurerResources.conjurer.tooltips.matter.title), ConjurerResources.conjurer.strings.conjurerTutorial4, ConjurerImages.Tutorial.etut03.texture())
        );
    }

    @Override
    public String getInfoTitle() {
        return ConjurerCharacter.NAMES[0];
    }

    public int getLevel(PCLAffinity affinity) {
        if (affinity == PCLAffinity.General) {
            return EUIUtils.max(elements, r -> r.level);
        }
        final ConjurerElementButton p = getElementButton(affinity);
        return p == null ? 0 : p.level;
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

    public void initialize() {
        super.initialize();
        for (ConjurerElementButton b : elements) {
            b.initialize();
        }
        matterPreview = matterCount = 0;
        set(GameUtilities.getRandomElement(PCLAffinity.getAvailableAffinities()), 0);
        if (lastUpgrade == null) {
            set(PCLAffinity.General, 0);
        }
        previewReactions = null;
        totalReactions = 0;

        addDefaultReactions();
    }

    public boolean isHighlighted() {
        return matterPreview != matterCount;
    }

    @Override
    public boolean isHovered() {
        return super.isHovered() || EUIUtils.any(elements, p -> p.hb.hovered);
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
        AffinityReactions reactions = info.getAux(this, AffinityReactions.class);
        if (reactions != null && !reactions.isEmpty()) {
            PCLActions.last.add(new ElementReaction(reactions, card, info.source, info.target));
        }
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

    @Override
    public void renderImpl(SpriteBatch sb) {
        super.renderImpl(sb);

        for (ConjurerElementButton element : elements) {
            element.tryRender(sb);
        }

        reactionCountText.tryRender(sb);
        reactionHeader.tryRender(sb);
    }

    @Override
    public PCLAffinity set(PCLAffinity affinity, int target) {
        lastUpgrade = affinity;
        EUIKeywordTooltip helper = ElementPowerData.get(lastUpgrade).getTooltip();
        return lastUpgrade;
    }

    @Override
    public void setupInfo(PCLUseInfo info) {
        AffinityReactions reactions = info.getAuxOrCreate(this, AffinityReactions.class);
        if (info.card != null && reactions != null) {
            // If the card is a summon that is played, it can only target a single slot
            if (info.card.type == PCLEnum.CardType.SUMMON && !(info.source instanceof PCLCardAlly && ((PCLCardAlly) info.source).card == info.card)) {
                ConjurerReactionMeter.meter.updateReactions(reactions, info.card, info.target != null ? Collections.singleton(info.target) : Collections.emptyList());
            }
            else {
                ConjurerReactionMeter.meter.updateReactions(reactions, info.card, info.targets);
            }
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
            ElementPowerData helper = ElementPowerData.get(affinity);
            order.applyPower(info.target, helper, value);
            return true;
        }
        return false;
    }

    @Override
    public void updateImpl(PCLCard card, PCLCard originalCard, AbstractCreature target, AbstractCreature originalTarget, boolean isDraggingCard, boolean shouldUpdateForCard, boolean shouldUpdateForTarget) {
        super.updateImpl(card, originalCard, target, originalTarget, isDraggingCard, shouldUpdateForCard, shouldUpdateForTarget);

        for (ConjurerElementButton element : elements) {
            element.tryUpdate();
        }

        if ((shouldUpdateForTarget || shouldUpdateForCard) && card != null) {
            // When you play the summon, you can only target an ally
            Collection<? extends AbstractCreature> targets = card.type == PCLEnum.CardType.SUMMON ? target != null ? Collections.singleton(target) : Collections.emptyList()
                    : card.pclTarget.getTargets(AbstractDungeon.player, target);
            previewReactions = getReactions(card, targets);

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
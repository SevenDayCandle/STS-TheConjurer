package pinacolada.dungeon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.*;
import com.megacrit.cardcrawl.cards.green.*;
import com.megacrit.cardcrawl.cards.purple.*;
import com.megacrit.cardcrawl.cards.red.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import extendedui.EUIUtils;
import extendedui.ui.controls.EUITutorialImagePage;
import extendedui.ui.controls.EUITutorialPage;
import extendedui.ui.hitboxes.RelativeHitbox;
import extendedui.ui.tooltips.EUIHeaderlessTooltip;
import extendedui.utilities.SeparableTrie;
import pinacolada.actions.PCLActions;
import pinacolada.actions.powers.ElementReaction;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardAffinities;
import pinacolada.cards.base.fields.PCLCardAffinity;
import pinacolada.monsters.PCLCardAlly;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.conjurer.*;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerImages;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.resources.pcl.PCLCoreImages;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.utilities.GameUtilities;

import java.util.*;

import static extendedui.EUIUtils.array;

public class ConjurerReactionMeter extends PCLPlayerMeter {
    private static final SeparableTrie<PCLAffinity[]> AFFINITY_TREE = new SeparableTrie<>();
    public static final String ID = createFullID(ConjurerResources.conjurer, ConjurerReactionMeter.class);
    public static final Color ACTIVE_COLOR = new Color(0.5f, 1f, 0.5f, 1f);
    public static final float ICON_SIZE = scale(48);
    public static final float BASE_AMOUNT_SCALE = 1f;
    public static final float BASE_CHARGE_SCALE = 0.6f;
    public static final float BUTTON_SCALE = 1.5f;
    public static final float OFFSET_SCALE_X = 2.5f * BUTTON_SCALE;
    public static final float OFFSET_SCALE_Y = -0.5f * BUTTON_SCALE;
    public static final float OFFSET_SPACING = BUTTON_SCALE * 0.7f;
    public static final ConjurerReactionMeter meter = new ConjurerReactionMeter();
    private AffinityReactions previewReactions;
    protected ArrayList<ConjurerElementButton> elements = new ArrayList<>();
    protected ConjurerElementButton fire;
    protected ConjurerElementButton water;
    protected ConjurerElementButton air;
    protected ConjurerElementButton earth;
    protected ConjurerElementButton light;
    protected ConjurerElementButton dark;
    protected EUIHeaderlessTooltip previewTip;
    protected int totalReactions;

    public ConjurerReactionMeter() {
        super(ID, ConjurerResources.conjurer.data.config.meterPosition, ConjurerResources.conjurer, ICON_SIZE);

        fire = new ConjurerElementButton(this, IgnisPower.DATA, ConjurerImages.Core.elementFire.texture(), RelativeHitbox.fromPercentages(hb, BUTTON_SCALE, BUTTON_SCALE, OFFSET_SCALE_X, OFFSET_SCALE_Y + OFFSET_SPACING));
        water = new ConjurerElementButton(this, AquaPower.DATA, ConjurerImages.Core.elementWater.texture(), RelativeHitbox.fromPercentages(hb, BUTTON_SCALE, BUTTON_SCALE, OFFSET_SCALE_X + OFFSET_SPACING, OFFSET_SCALE_Y));
        air = new ConjurerElementButton(this, VentusPower.DATA, ConjurerImages.Core.elementAir.texture(), RelativeHitbox.fromPercentages(hb, BUTTON_SCALE, BUTTON_SCALE, OFFSET_SCALE_X, OFFSET_SCALE_Y - OFFSET_SPACING));
        earth = new ConjurerElementButton(this, PetraPower.DATA, ConjurerImages.Core.elementEarth.texture(), RelativeHitbox.fromPercentages(hb, BUTTON_SCALE, BUTTON_SCALE, OFFSET_SCALE_X - OFFSET_SPACING, OFFSET_SCALE_Y));
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

        previewTip = new EUIHeaderlessTooltip(EUIUtils.EMPTY_STRING);

        initializeTrie();
    }

    private static void initializeTrie() {
        AFFINITY_TREE.putAll(EUIUtils.array(PCLAffinity.Red),"Fire", "Flame", "Burn", "Scorc", "Heat", "Solar", "Explod", "Explosion", "Blast", "Ignit", "Pyro");
        AFFINITY_TREE.putAll(EUIUtils.array(PCLAffinity.Red, PCLAffinity.Green),"Smoke");
        AFFINITY_TREE.putAll(EUIUtils.array(PCLAffinity.Red, PCLAffinity.Blue),"Steam", "Geyser", "Boil");
        AFFINITY_TREE.putAll(EUIUtils.array(PCLAffinity.Red, PCLAffinity.Orange),"Lava", "Magma", "Volcan");
        AFFINITY_TREE.putAll(EUIUtils.array(PCLAffinity.Blue),"Water", "Ice", "Icicle", "Snow", "Frost", "Chill", "Cold", "Freeze", "Froze", "Aqua", "Ocean", "Bubble", "Liquid", "Cool", "Crystal", "Hydro", "Cryo", "Flood");
        AFFINITY_TREE.putAll(EUIUtils.array(PCLAffinity.Blue, PCLAffinity.Green),"Storm", "Mist", "Fog", "Cloud", "Weather", "Hurricane", "Vapor");
        AFFINITY_TREE.putAll(EUIUtils.array(PCLAffinity.Blue, PCLAffinity.Orange),"Mud", "Swamp", "Sludge");
        AFFINITY_TREE.putAll(EUIUtils.array(PCLAffinity.Green),"Wind", "Sky", "Poison", "Toxic", "Air", "Smoke", "Breeze", "Tornado", "Leaf", "Blossom", "Flower", "Anemo");
        AFFINITY_TREE.putAll(EUIUtils.array(PCLAffinity.Green, PCLAffinity.Orange),"Nature", "Wood", "Forest", "Grass", "Bloom", "Plant", "Tree");
        AFFINITY_TREE.putAll(EUIUtils.array(PCLAffinity.Orange),"Earth", "Rock", "Stone", "Ground", "Land", "Geo");
        AFFINITY_TREE.putAll(EUIUtils.array(PCLAffinity.Yellow),"Electr", "Thund", "Shock", "Volt", "Holy", "Bless", "Sacred", "Photo", "Light", "Plasm");
        AFFINITY_TREE.putAll(EUIUtils.array(PCLAffinity.Purple),"Dark", "Shado", "Evil", "Night", "Curs", "Void", "Corrupt", "infinitespire", "Umbr");
        AFFINITY_TREE.putAll(EUIUtils.array(PCLAffinity.Silver),"Metal", "Steel", "Iron");
        AFFINITY_TREE.putAll(EUIUtils.array(PCLAffinity.Star),"Rainbow");
    }

    public void addDefaultReactions() {
        addReaction(fire, water, PMove.applyToSingle(3, BlastedPower.DATA).setUpgrade(2))
                .addUpgrade(PMove.applyToSingle(1, PCLPowerData.Vulnerable), 2);
        addReaction(fire, air, PMove.gainPlayer(3, PCLPowerData.Vigor).setUpgrade(2))
                .addUpgrade(PMove.dealDamageToAll(3), 1);
        addReaction(fire, earth, PMove.gainPlayer(3, ForgingPower.DATA).setUpgrade(2))
                .addUpgrade(PMove.upgrade(1).edit(f -> f.setRandom(true)), 2);
        addReaction(water, air, PMove.gainPlayer(3, FlowPower.DATA).setUpgrade(2))
                .addUpgrade(PMove.draw(1), 1);
        addReaction(water, earth, PMove.applyToSingle(4, CooledPower.DATA).setUpgrade(3))
                .addUpgrade(PMove.applyToSingle(1, PCLPowerData.Weak), 1);
        addReaction(air, earth, PMove.gainPlayer(3, PCLPowerData.Warding))
                .addUpgrade(PMove.gainTempHP(2), 1)
                .addUpgrade(PMove.applyToSingle(2, PCLPowerData.Poison), 1);
    }

    public ConjurerReactionGroup addReaction(ConjurerElementButton a1, ConjurerElementButton a2, PSkill<?>... skills) {
        ConjurerReactionGroup group = new ConjurerReactionGroup(a1.power.affinity, a1.power.affinity, Arrays.asList(skills));
        a1.reactions.put(a2.power.affinity, group);
        a2.reactions.put(a1.power.affinity, group);
        return group;
    }

    public void addLevel(PCLAffinity affinity, int amount) {
        final ConjurerElementButton p = getElementButton(affinity);
        if (p != null) {
            p.addLevel(amount);
        }
    }

    public void applyAffinities(AbstractCard card) {
        PCLAffinity[] res = getAffinitiesForNonPCL(card);
        if (res != null) {
            for (PCLAffinity af : res) {
                GameUtilities.modifyAffinityLevel(card, af, 1, true);
            }
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
                                    reactions.addReaction(button.power.affinity, af.type, m, button.reactionGain(po, af));
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

    public PCLAffinity[] getAffinitiesForNonPCL(AbstractCard card) {
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
                return EUIUtils.array(PCLAffinity.Red);
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
                return EUIUtils.array(PCLAffinity.Green);
            case Blizzard.ID:
            case Chill.ID:
            case ColdSnap.ID:
            case Glacier.ID:
            case LikeWater.ID:
            case Study.ID:
            case Tranquility.ID:
                return EUIUtils.array(PCLAffinity.Blue);
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
                return EUIUtils.array(PCLAffinity.Orange);
            case DualWield.ID:
            case SwordBoomerang.ID:
                return EUIUtils.array(PCLAffinity.Red, PCLAffinity.Green);
            case Adrenaline.ID:
            case FeelNoPain.ID:
            case TrueGrit.ID:
                return EUIUtils.array(PCLAffinity.Red, PCLAffinity.Orange);
            case Carnage.ID:
            case DemonForm.ID:
                return EUIUtils.array(PCLAffinity.Red, PCLAffinity.Purple);
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
                return EUIUtils.array(PCLAffinity.Yellow);
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
                return EUIUtils.array(PCLAffinity.Purple);
            case CreativeAI.ID:
            case DeusExMachina.ID:
            case FTL.ID:
            case MasterReality.ID:
            case Metallicize.ID:
            case SpotWeakness.ID:
                return EUIUtils.array(PCLAffinity.Silver);
            case Chaos.ID:
            case Rainbow.ID:
                return EUIUtils.array(PCLAffinity.Star);
        }

        return AFFINITY_TREE.get(card.cardID);
    }

    public ConjurerElementButton getElementButton(PCLAffinity affinity) {
        return affinity.ID >= 0 && affinity.ID < elements.size() ? elements.get(affinity.ID) : null;
    }

    public ArrayList<ConjurerElementButton> getElementButtons() {
        return elements;
    }

    private ArrayList<AbstractElementPower> getElementalPowers(AbstractCreature c) {
        return c != null && c.powers != null ? EUIUtils.mapAsNonnull(c.powers, po -> EUIUtils.safeCast(po, AbstractElementPower.class)) : new ArrayList<>();
    }

    @Override
    public String getInfoMainDescrption() {
        return ConjurerResources.conjurer.strings.conjurerSimple;
    }

    @Override
    public EUITutorialPage[] getInfoPages() {
        return array(
                new EUITutorialImagePage(makeTitle(PGR.core.strings.misc_fabricate, PGR.core.tooltips.affinityGeneral.title), PGR.core.strings.tutorial_affinityTutorial, PCLCoreImages.Tutorial.affTut01.texture()),
                new EUITutorialImagePage(makeTitle(PGR.core.strings.misc_fabricate, PGR.core.tooltips.summon.title, 1), PGR.core.strings.tutorial_summonTutorial1, PCLCoreImages.Tutorial.sumTut01.texture()),
                new EUITutorialImagePage(makeTitle(PGR.core.strings.misc_fabricate, PGR.core.tooltips.summon.title, 2), PGR.core.strings.tutorial_summonTutorial2, PCLCoreImages.Tutorial.sumTut02.texture()),
                new EUITutorialImagePage(makeTitle(PGR.core.strings.misc_fabricate, PGR.core.tooltips.summon.title, 3), PGR.core.strings.tutorial_summonTutorial3, PCLCoreImages.Tutorial.sumTut03.texture()),
                new EUITutorialImagePage(makeTitle(PGR.core.strings.misc_fabricate, PGR.core.tooltips.summon.title, 4), PGR.core.strings.tutorial_summonTutorial4, PCLCoreImages.Tutorial.sumTut03.texture()),
                new EUITutorialImagePage(makeTitle(PGR.core.strings.misc_fabricate, PGR.core.tooltips.summon.title, 5), PGR.core.strings.tutorial_summonTutorial5, PCLCoreImages.Tutorial.sumTut03.texture()),
                new EUITutorialImagePage(makeTitle(PGR.core.strings.misc_fabricate, PGR.core.tooltips.summon.title, 6), PGR.core.strings.tutorial_summonTutorial6, PCLCoreImages.Tutorial.sumTut04.texture()),
                new EUITutorialImagePage(makeTitle(PGR.core.strings.misc_fabricate, PGR.core.tooltips.summon.title, 7), PGR.core.strings.tutorial_summonTutorial7, PCLCoreImages.Tutorial.sumTut05.texture()),
                new EUITutorialImagePage(makeTitle(PGR.core.strings.misc_fabricate, PGR.core.tooltips.summon.title, 8), PGR.core.strings.tutorial_summonTutorial8, PCLCoreImages.Tutorial.sumTut06.texture()),
                new EUITutorialImagePage(makeTitle(getInfoTitle(), ConjurerResources.conjurer.tooltips.element.title), ConjurerResources.conjurer.strings.conjurerTutorial1, ConjurerImages.Tutorial.etut01.texture()),
                new EUITutorialImagePage(makeTitle(getInfoTitle(), ConjurerResources.conjurer.tooltips.reaction.title, 1), ConjurerResources.conjurer.strings.conjurerTutorial2, ConjurerImages.Tutorial.etut02.texture()),
                new EUITutorialImagePage(makeTitle(getInfoTitle(), ConjurerResources.conjurer.tooltips.reaction.title, 2), ConjurerResources.conjurer.strings.conjurerTutorial3, ConjurerImages.Tutorial.etut03.texture())
        );
    }

    @Override
    public String getInfoTitle() {
        return ConjurerResources.conjurer.data.getCharacterStrings().NAMES[0];
    }

    public int getLevel(PCLAffinity affinity) {
        if (affinity == PCLAffinity.General) {
            return EUIUtils.max(elements, ConjurerElementButton::getLevel);
        }
        final ConjurerElementButton p = getElementButton(affinity);
        return p == null ? 0 : p.getLevel();
    }

    public AffinityReactions getPreviewReactions() {
        return previewReactions;
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

    public void initialize() {
        super.initialize();
        for (ConjurerElementButton b : elements) {
            b.initialize();
        }
        previewReactions = null;
        totalReactions = 0;

        addDefaultReactions();
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

    // Block/Damage multipliers scale additively
    @Override
    public float modifyBlock(float block, PCLUseInfo info, PCLCard source, PCLCard card) {
        if (info.target != null) {
            float newBlock = 0;
            for (PCLCardAffinity aff : source.affinities.getCardAffinities(true)) {
                for (AbstractPower p : info.target.powers) {
                    for (ConjurerElementButton element : getElementButtons()) {
                        if (element.canReact(aff.type, p.ID)) {
                            for (PSkill<?> sk : element.getReactEffects(aff.type)) {
                                for (int i = 0; i < aff.level; i++) {
                                    newBlock = newBlock + sk.modifyBlockFirst(info, block) - block;
                                }
                            }
                        }
                    }
                }
            }
            newBlock += block;
            card.addDefendDisplay(PCLAffinity.General, block, newBlock);
            return newBlock;
        }
        return block;
    }

    @Override
    public float modifyDamage(float damage, PCLUseInfo info, PCLCard source, PCLCard card) {
        if (info.target != null) {
            float newDamage = 0;
            for (PCLCardAffinity aff : source.affinities.getCardAffinities(true)) {
                for (AbstractPower p : info.target.powers) {
                    for (ConjurerElementButton element : getElementButtons()) {
                        if (element.canReact(aff.type, p.ID)) {
                            for (PSkill<?> sk : element.getReactEffects(aff.type)) {
                                for (int i = 0; i < aff.level; i++) {
                                    newDamage = newDamage + sk.modifyDamageGiveFirst(info, damage) - damage;
                                }
                            }
                        }
                    }
                }
            }
            newDamage += damage;
            card.addAttackDisplay(PCLAffinity.General, damage, newDamage);
            return newDamage;
        }
        return damage;
    }

    @Override
    public void onCardCreated(AbstractCard card, boolean startOfBattle) {
        super.onCardCreated(card, startOfBattle);
        if (!(card instanceof PCLCard)) {
            applyAffinities(card);
        }
    }

    @Override
    public void onCardPlayed(PCLCard card, PCLUseInfo info, boolean fromSummon) {
        // Do not trigger reactions from playing your summons on top of existing ones
        AffinityReactions reactions = info.getAux(this, AffinityReactions.class);
        if (reactions != null && !reactions.isEmpty() && (fromSummon || card.type != PCLEnum.CardType.SUMMON)) {
            PCLActions.bottom.add(new ElementReaction(reactions, card, info));
        }
    }

    public void onReaction(AffinityReactions reactions, boolean showEffect) {
        if (reactions.hasReaction()) {
            totalReactions += reactions.sum();
            for (ConjurerElementButton element : elements) {
                element.onReactionPost(reactions, showEffect);
            }
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

        if (!previewTip.description.isEmpty()) {
            previewTip.render(sb, hb.cX, hb.y - hb.height * 4, 0);
        }
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

            if (shouldUpdateForTarget) {
                if (target != null) {
                    StringJoiner sj = new StringJoiner(EUIUtils.SPLIT_LINE);
                    for (ConjurerElementButton element : elements) {
                        element.updatePreview(previewReactions, sj);
                    }
                    previewTip.setDescription(sj.toString());
                }
                else {
                    previewTip.description = EUIUtils.EMPTY_STRING;
                }
            }
        }
        else if (card == null) {
            previewTip.description = EUIUtils.EMPTY_STRING;
            for (ConjurerElementButton element : elements) {
                element.unsetPreview();
            }
        }
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

package pinacolada.monsters.tutorials;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import extendedui.ui.controls.EUIImage;
import extendedui.ui.hitboxes.EUIHitbox;
import extendedui.ui.tooltips.EUITourTooltip;
import pinacolada.actions.powers.ElementReaction;
import pinacolada.actions.utility.SelectCreature;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.conjurer.basic.Condensation;
import pinacolada.cards.conjurer.basic.Ignite;
import pinacolada.cards.conjurer.series.genshinimpact.Bennett;
import pinacolada.cards.conjurer.series.genshinimpact.KaeyaAlberich;
import pinacolada.cards.conjurer.series.shinmegamitensei.JackFrost;
import pinacolada.cards.conjurer.series.shinmegamitensei.PyroJack;
import pinacolada.dungeon.ConjurerReactionButton;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.monsters.PCLCardAlly;
import pinacolada.monsters.PCLCreatureData;
import pinacolada.monsters.PCLTutorialMonster;
import pinacolada.powers.conjurer.PetraPower;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.resources.pcl.PCLCoreImages;
import pinacolada.utilities.GameUtilities;

public class ConjurerSummonTutorialMonster extends PCLTutorialMonster {

    public static final PCLCreatureData DATA = register(ConjurerSummonTutorialMonster.class, ConjurerResources.conjurer)
            .setHp(999)
            .setHb(0.0F, 0F, 128, 128);
    protected final Color renderColor = Color.SKY.cpy();

    public ConjurerSummonTutorialMonster() {
        super(DATA);
        this.loadAnimation("images/monsters/theBottom/slimeS/skeleton.atlas", "images/monsters/theBottom/slimeS/skeleton.json", 1.0F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
        addSteps(this::step1, this::step2, this::step3, this::step4);
    }

    public void renderAnimation(SpriteBatch sb) {
        this.renderAnimation(sb, renderColor);
    }

    public EUITourTooltip step1() {
        AbstractCard card = new Bennett();
        replaceHandWith(card);

        Hitbox targetHb = card.hb;
        for (PCLCardAlly ally : GameUtilities.getSummons(null)) {
            if (ally != null) {
                targetHb = ally.hb;
                break;
            }
        }

        EUITourTooltip.queueTutorial(AbstractDungeon.CurrentScreen.NONE, new EUITourTooltip(targetHb, PGR.core.tooltips.summon.title, PGR.core.strings.tutorial_summonTutorial1)
                .setCanDismiss(true));
        EUITourTooltip.queueTutorial(AbstractDungeon.CurrentScreen.NONE, new EUITourTooltip(targetHb, PGR.core.tooltips.summon.title, PGR.core.strings.tutorial_summonTutorial2)
                .setFlash(new EUIImage(PCLCoreImages.Monsters.emptyShadow.texture(), new EUIHitbox(targetHb)))
                .setCanDismiss(true));
        return new EUITourTooltip(UseCardAction.class, PGR.core.tooltips.summon.title, ConjurerResources.conjurer.strings.conjurerSummonInteractive1)
                .setPosition(hb.x - hb.width * 3, hb.y + hb.height);
    }

    public EUITourTooltip step2() {
        Hitbox targetHb = AbstractDungeon.player.hb;
        for (PCLCardAlly ally : GameUtilities.getSummons(true)) {
            if (ally != null) {
                targetHb = ally.hb;
                break;
            }
        }

        EUITourTooltip.queueTutorial(AbstractDungeon.CurrentScreen.NONE, new EUITourTooltip(targetHb, PGR.core.tooltips.summon.title, PGR.core.strings.tutorial_summonTutorial3)
                .setCanDismiss(true));
        EUITourTooltip.queueTutorial(AbstractDungeon.CurrentScreen.NONE, new EUITourTooltip(targetHb, PGR.core.tooltips.summon.title, PGR.core.strings.tutorial_summonTutorial4)
                .setCanDismiss(true));
        return new EUITourTooltip(SelectCreature.class, PGR.core.tooltips.summon.title, PGR.core.strings.tutorial_summonTutorial5)
                .setPosition(targetHb.x, targetHb.y)
                .setCanDismiss(false);
    }

    public EUITourTooltip step3() {
        AbstractCard pyro = new PyroJack();
        replaceHandWith(pyro, new KaeyaAlberich(), new JackFrost());

        PetraPower p = new PetraPower(this, this, 5);
        p.stabilize(1);
        powers.add(p);

        Hitbox targetHb = AbstractDungeon.player.hb;
        for (PCLCardAlly ally : GameUtilities.getSummons(true)) {
            if (ally != null) {
                targetHb = ally.hb;
                break;
            }
        }
        ConjurerReactionButton button = ConjurerReactionMeter.meter.getReactionButton(PCLAffinity.Orange, PCLAffinity.Red);

        EUITourTooltip.queueTutorial(AbstractDungeon.CurrentScreen.NONE, new EUITourTooltip(targetHb, PGR.core.tooltips.summon.title, PGR.core.strings.tutorial_summonTutorial5)
                .setCanDismiss(true));
        EUITourTooltip.queueTutorial(AbstractDungeon.CurrentScreen.NONE, new EUITourTooltip(button, PGR.core.tooltips.summon.title, ConjurerResources.conjurer.strings.conjurerSummonInteractive2)
                .setCanDismiss(true));
        return new EUITourTooltip(ElementReaction.class, PGR.core.tooltips.summon.title, ConjurerResources.conjurer.strings.conjurerSummonInteractive3)
                .setPosition(hb.x - hb.width * 3, hb.y + hb.height)
                .setCanDismiss(false);
    }

    public EUITourTooltip step4() {
        this.setMove("", (byte) -1, Intent.ATTACK, 30, 2, true);

        replaceHandWith(new Condensation(), new Ignite());

        Hitbox targetHb = AbstractDungeon.player.hb;
        for (PCLCardAlly ally : GameUtilities.getSummons(true)) {
            if (ally != null) {
                targetHb = ally.hb;
                break;
            }
        }
        EUITourTooltip.queueTutorial(AbstractDungeon.CurrentScreen.NONE, new EUITourTooltip(targetHb, PGR.core.tooltips.summon.title, PGR.core.strings.tutorial_summonTutorial6)
                .setCanDismiss(true));
        EUITourTooltip.queueTutorial(AbstractDungeon.CurrentScreen.NONE, new EUITourTooltip(targetHb, PGR.core.tooltips.summon.title, PGR.core.strings.tutorial_summonTutorial7)
                .setCanDismiss(true));
        return new EUITourTooltip(UseCardAction.class, PGR.core.tooltips.summon.title, ConjurerResources.conjurer.strings.conjurerSummonInteractive4)
                .setPosition(hb.x - hb.width * 3, hb.y + hb.height)
                .setCanDismiss(false);
    }
}

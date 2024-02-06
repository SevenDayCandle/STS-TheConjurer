package pinacolada.monsters.tutorials;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import extendedui.ui.tooltips.EUITourTooltip;
import pinacolada.actions.powers.ElementReaction;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.conjurer.basic.Condensation;
import pinacolada.cards.conjurer.basic.Ignite;
import pinacolada.cards.conjurer.basic.Lithosphere;
import pinacolada.dungeon.ConjurerElementButton;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.monsters.PCLCreatureData;
import pinacolada.monsters.PCLTutorialMonster;
import pinacolada.powers.conjurer.PetraPower;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerResources;

public class ConjurerTutorialMonster extends PCLTutorialMonster {

    public static final PCLCreatureData DATA = register(ConjurerTutorialMonster.class, ConjurerResources.conjurer)
            .setHp(999)
            .setHb(0.0F, 0F, 128, 128);
    protected final Color renderColor = Color.SALMON.cpy();

    public ConjurerTutorialMonster() {
        super(DATA);
        this.loadAnimation("images/monsters/theBottom/slimeS/skeleton.atlas", "images/monsters/theBottom/slimeS/skeleton.json", 1.0F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
        this.tint.color = Color.SALMON.cpy();
        addSteps(this::step1, this::step2);
    }

    public void renderAnimation(SpriteBatch sb) {
        this.renderAnimation(sb, renderColor);
    }

    public EUITourTooltip step1() {
        AbstractCard card = new Lithosphere();
        replaceHandWith(card);
        ConjurerReactionMeter.meter.initialize();
        ConjurerElementButton button = ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Red);
        ConjurerReactionMeter.meter.hb.move(ConjurerReactionMeter.meter.hb.targetCx, ConjurerReactionMeter.meter.hb.targetCy);
        button.hb.update();
        EUITourTooltip.queueTutorial(AbstractDungeon.CurrentScreen.NONE, new EUITourTooltip(card.hb, ConjurerResources.conjurer.tooltips.element.title, PGR.core.strings.tutorial_affinityTutorial)
                .setPosition(hb.x - hb.width * 4, hb.y + hb.height)
                .setCanDismiss(true));
        EUITourTooltip.queueTutorial(AbstractDungeon.CurrentScreen.NONE, new EUITourTooltip(button, ConjurerResources.conjurer.tooltips.element.title, ConjurerResources.conjurer.strings.conjurerInteractive1)
                .setPosition(Settings.WIDTH * 0.5f, Settings.HEIGHT * 0.6f)
                .setCanDismiss(true));
        EUITourTooltip.queueTutorial(AbstractDungeon.CurrentScreen.NONE, new EUITourTooltip(button, ConjurerResources.conjurer.tooltips.element.title, ConjurerResources.conjurer.strings.conjurerTutorial1)
                .setPosition(Settings.WIDTH * 0.5f, Settings.HEIGHT * 0.6f)
                .setCanDismiss(true));
        return new EUITourTooltip(UseCardAction.class, ConjurerResources.conjurer.tooltips.element.title, ConjurerResources.conjurer.strings.conjurerInteractive2)
                .setPosition(hb.x - hb.width * 4, hb.y + hb.height)
                .setCanDismiss(false);
    }

    public EUITourTooltip step2() {
        replaceHandWith(new Ignite(), new Condensation());
        PetraPower p = new PetraPower(this, this, 5);
        p.addTurns(2);
        powers.add(p);

        return new EUITourTooltip(ElementReaction.class, ConjurerResources.conjurer.tooltips.element.title, ConjurerResources.conjurer.strings.conjurerInteractive3)
                .setPosition(hb.x - hb.width * 4, hb.y + hb.height)
                .setCanDismiss(false);
    }
}

package pinacolada.monsters.tutorials;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import extendedui.ui.tooltips.EUITourTooltip;
import pinacolada.actions.powers.ElementReaction;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.conjurer.basic.Condensation;
import pinacolada.cards.conjurer.basic.Ignite;
import pinacolada.cards.conjurer.basic.Lithosphere;
import pinacolada.cards.conjurer.series.eldenring.Lucidity;
import pinacolada.monsters.PCLCreatureData;
import pinacolada.monsters.PCLTutorialMonster;
import pinacolada.powers.conjurer.PetraPower;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.ui.combat.ConjurerElementButton;
import pinacolada.ui.combat.ConjurerReactionButton;
import pinacolada.ui.combat.ConjurerReactionMeter;

public class ConjurerTutorialMonster extends PCLTutorialMonster {

    public static final PCLCreatureData DATA = register(ConjurerTutorialMonster.class, ConjurerResources.conjurer)
            .setHp(999999)
            .setHb(0.0F, 0F, 128, 128);
    protected final Color renderColor = Color.SALMON.cpy();

    public ConjurerTutorialMonster() {
        super(DATA);
        this.loadAnimation("images/monsters/theBottom/slimeS/skeleton.atlas", "images/monsters/theBottom/slimeS/skeleton.json", 1.0F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
        this.tint.color = Color.SALMON.cpy();
        addSteps(this::step1, this::step2, this::step3);
    }

    public void renderAnimation(SpriteBatch sb) {
        this.renderAnimation(sb, renderColor);
    }

    public EUITourTooltip step1() {
        AbstractCard card = new Lithosphere();
        replaceHandWith(card);
        ConjurerElementButton button = ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Red);
        EUITourTooltip.queueTutorial(AbstractDungeon.CurrentScreen.NONE, new EUITourTooltip(button, ConjurerResources.conjurer.tooltips.elementalDebuff.title, ConjurerResources.conjurer.strings.conjurerInteractive1)
                .setCanDismiss(true));
        EUITourTooltip.queueTutorial(AbstractDungeon.CurrentScreen.NONE, new EUITourTooltip(button, ConjurerResources.conjurer.tooltips.elementalDebuff.title, ConjurerResources.conjurer.strings.conjurerTutorial1)
                .setCanDismiss(true));
        EUITourTooltip.queueTutorial(AbstractDungeon.CurrentScreen.NONE, new EUITourTooltip(card.hb, ConjurerResources.conjurer.tooltips.elementalDebuff.title, PGR.core.strings.tutorial_affinityTutorial)
                        .setPosition(hb.x - hb.width * 3, hb.y + hb.height)
                .setCanDismiss(true));
        return new EUITourTooltip(UseCardAction.class, ConjurerResources.conjurer.tooltips.elementalDebuff.title, ConjurerResources.conjurer.strings.conjurerInteractive2)
                .setPosition(hb.x - hb.width * 3, hb.y + hb.height)
                .setCanDismiss(false);
    }

    public EUITourTooltip step2() {
        replaceHandWith(new Ignite(), new Condensation());
        PetraPower p = new PetraPower(this, this, 5);
        p.stabilize(1);
        powers.add(p);
        ConjurerReactionButton button = ConjurerReactionMeter.meter.getReactionButton(PCLAffinity.Orange, PCLAffinity.Red);
        EUITourTooltip.queueTutorial(AbstractDungeon.CurrentScreen.NONE, new EUITourTooltip(button, ConjurerResources.conjurer.tooltips.elementalDebuff.title, ConjurerResources.conjurer.strings.conjurerTutorial2).setCanDismiss(true));
        EUITourTooltip.queueTutorial(AbstractDungeon.CurrentScreen.NONE, new EUITourTooltip(button, ConjurerResources.conjurer.tooltips.elementalDebuff.title, ConjurerResources.conjurer.strings.conjurerTutorial3).setCanDismiss(true));
        return new EUITourTooltip(ElementReaction.class, ConjurerResources.conjurer.tooltips.elementalDebuff.title, ConjurerResources.conjurer.strings.conjurerInteractive3)
                .setPosition(hb.x - hb.width * 3, hb.y + hb.height)
                .setCanDismiss(false);
    }

    public EUITourTooltip step3() {
        replaceHandWith(new Lucidity());
        ConjurerReactionMeter.meter.disableAffinity(PCLAffinity.Blue);
        ConjurerReactionMeter.meter.disableAffinity(PCLAffinity.Green);
        ConjurerReactionMeter.meter.disableAffinity(PCLAffinity.Orange);
        ConjurerReactionMeter.meter.addCount(15, true);
        ConjurerElementButton button = ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Red);
        EUITourTooltip.queueTutorial(AbstractDungeon.CurrentScreen.NONE, new EUITourTooltip(button, ConjurerResources.conjurer.tooltips.elementalDebuff.title, ConjurerResources.conjurer.strings.conjurerTutorial4).setCanDismiss(true));
        return new EUITourTooltip(button.hb, ConjurerResources.conjurer.tooltips.elementalDebuff.title, ConjurerResources.conjurer.strings.conjurerInteractive4)
                .setPosition(hb.x - hb.width * 3, hb.y + hb.height)
                .setCanDismiss(false);
    }
}
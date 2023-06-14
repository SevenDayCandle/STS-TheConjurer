package pinacolada.monsters.tutorials;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import extendedui.ui.tooltips.EUITourTooltip;
import pinacolada.actions.powers.ElementReaction;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.conjurer.basic.Condensation;
import pinacolada.cards.conjurer.basic.Ignite;
import pinacolada.cards.conjurer.basic.Lithosphere;
import pinacolada.cards.conjurer.series.eldenring.Lucidity;
import pinacolada.effects.PCLEffects;
import pinacolada.effects.vfx.SmokeEffect;
import pinacolada.monsters.PCLCreatureData;
import pinacolada.monsters.PCLTutorialMonster;
import pinacolada.powers.conjurer.PetraPower;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.ui.combat.ConjurerElementButton;
import pinacolada.ui.combat.ConjurerReactionButton;
import pinacolada.ui.combat.ConjurerReactionMeter;

public class ConjurerTutorialMonster extends PCLTutorialMonster {

    public static final PCLCreatureData DATA = register(ConjurerTutorialMonster.class)
            .setHp(999999)
            .setHb(0.0F, -4.0F, 128, 128);

    public ConjurerTutorialMonster() {
        super(DATA);
        this.loadAnimation("images/monsters/theBottom/slimeS/skeleton.atlas", "images/monsters/theBottom/slimeS/skeleton.json", 1.0F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        addSteps(this::step1, this::step2, this::step3);
    }

    @Override
    public void usePreBattleAction() {
        super.usePreBattleAction();
        PCLEffects.Queue.add(new SmokeEffect(hb.cX, hb.cY));
    }

    public EUITourTooltip step1() {
        AbstractCard card = new Lithosphere();
        replaceHandWith(card);
        ConjurerElementButton button = ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Red);
        EUITourTooltip.queueTutorial(new EUITourTooltip(button, ConjurerResources.conjurer.tooltips.elementalDebuff.title, ConjurerResources.conjurer.strings.conjurerInteractive1)
                .setCanDismiss(true));
        EUITourTooltip.queueTutorial(new EUITourTooltip(button, ConjurerResources.conjurer.tooltips.elementalDebuff.title, ConjurerResources.conjurer.strings.conjurerTutorial1)
                .setCanDismiss(true));
        EUITourTooltip.queueTutorial(new EUITourTooltip(card.hb, ConjurerResources.conjurer.tooltips.elementalDebuff.title, PGR.core.strings.tutorial_affinityTutorial)
                        .setPosition(hb.x - hb.width * 3, hb.y + hb.height)
                .setCanDismiss(true));
        return new EUITourTooltip(UseCardAction.class, ConjurerResources.conjurer.tooltips.elementalDebuff.title, ConjurerResources.conjurer.strings.conjurerInteractive2)
                .setPosition(hb.x - hb.width * 3, hb.y + hb.height);
    }

    public EUITourTooltip step2() {
        replaceHandWith(new Ignite(), new Condensation());
        PetraPower p = new PetraPower(this, this, 5);
        p.stabilize(1);
        powers.add(p);
        ConjurerReactionButton button = ConjurerReactionMeter.meter.getReactionButton(PCLAffinity.Orange, PCLAffinity.Red);
        EUITourTooltip.queueTutorial(new EUITourTooltip(button, ConjurerResources.conjurer.tooltips.elementalDebuff.title, ConjurerResources.conjurer.strings.conjurerTutorial2).setCanDismiss(true));
        EUITourTooltip.queueTutorial(new EUITourTooltip(button, ConjurerResources.conjurer.tooltips.elementalDebuff.title, ConjurerResources.conjurer.strings.conjurerTutorial3).setCanDismiss(true));
        return new EUITourTooltip(ElementReaction.class, ConjurerResources.conjurer.tooltips.elementalDebuff.title, ConjurerResources.conjurer.strings.conjurerInteractive3)
                .setPosition(hb.x - hb.width * 3, hb.y + hb.height);
    }

    public EUITourTooltip step3() {
        replaceHandWith(new Lucidity());
        ConjurerReactionMeter.meter.disableAffinity(PCLAffinity.Blue);
        ConjurerReactionMeter.meter.disableAffinity(PCLAffinity.Green);
        ConjurerReactionMeter.meter.disableAffinity(PCLAffinity.Orange);
        ConjurerReactionMeter.meter.addCount(15, true);
        ConjurerElementButton button = ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Red);
        EUITourTooltip.queueTutorial(new EUITourTooltip(button, ConjurerResources.conjurer.tooltips.elementalDebuff.title, ConjurerResources.conjurer.strings.conjurerTutorial4).setCanDismiss(true));
        return new EUITourTooltip(button.hb, ConjurerResources.conjurer.tooltips.elementalDebuff.title, ConjurerResources.conjurer.strings.conjurerInteractive4)
                .setPosition(hb.x - hb.width * 3, hb.y + hb.height);
    }
}

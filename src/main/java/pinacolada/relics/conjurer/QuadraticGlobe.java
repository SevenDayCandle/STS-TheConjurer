package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardAffinities;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.ui.combat.ConjurerReactionMeter;
import pinacolada.utilities.GameUtilities;

@VisibleRelic
public class QuadraticGlobe extends PCLRelic {
    public static final PCLRelicData DATA = register(QuadraticGlobe.class, ConjurerResources.conjurer)
            .setProps(RelicTier.BOSS, LandingSound.MAGICAL);

    public QuadraticGlobe() {
        super(DATA);
    }

    @Override
    protected void activateBattleEffect() {
        PCLActions.bottom.callback(() -> {
            ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Blue).addReaction(PCLAffinity.Red);
            ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Red).addReaction(PCLAffinity.Orange);
            ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Orange).addReaction(PCLAffinity.Green);
            ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Green).addReaction(PCLAffinity.Blue);
        });
    }

    @Override
    public float atDamageModify(PCLUseInfo info, float damage, AbstractCard c) {
        damage = super.atDamageModify(info, damage, c);
        PCLCardAffinities affinities = GameUtilities.getPCLCardAffinities(c);
        if (info != null && info.target != null && affinities != null) {
            float mult = 1f;
            float oldDamage = damage;
            for (PCLAffinity aff : affinities.getAffinities()) {
                for (AbstractPower p : info.target.powers) {
                    if (ConjurerReactionMeter.meter.isPowerElemental(p.ID, aff)) {
                        mult -= (100 - getValue()) / 100f;
                    }
                }
            }
            damage *= mult;
        }
        return damage;
    }


    public int getValue() {
        return 20;
    }
}
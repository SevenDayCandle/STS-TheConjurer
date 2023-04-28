package pinacolada.cards.conjurer.basic;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.VFX;
import pinacolada.interfaces.subscribers.OnSpecificPowerActivatedSubscriber;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.powers.conjurer.FlowPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class VerdantGale extends PCLCard {
    public static final PCLCardData DATA = register(VerdantGale.class, ConjurerResources.conjurer)
            .setPower(3, CardRarity.RARE)
            .setAffinities(2, PCLAffinity.Green)
            .setUTags(PCLCardTag.Retain)
            .setCore();

    public VerdantGale() {
        super(DATA);
    }

    public void setup(Object input) {
        addSpecialPower(0, (s, i) -> new VerdantGalePower(i.source, s), 4).setUpgrade(1);
    }

    public static class VerdantGalePower extends PSpecialCardPower implements OnSpecificPowerActivatedSubscriber {
        public VerdantGalePower(AbstractCreature owner, PSkill<?> move) {
            super(VerdantGale.DATA, owner, move);
        }

        @Override
        public void onInitialApplication() {
            super.onInitialApplication();

            PCLActions.bottom.playVFX(VFX.razorWind(owner.hb));
        }

        @Override
        public boolean onPowerActivated(AbstractPower power, AbstractCreature source, boolean originalValue) {
            if (power instanceof FlowPower) {
                for (AbstractMonster enemy : GameUtilities.getEnemies(true)) {
                    move.getActions().applyPower(enemy, PCLCardTarget.Single, PCLPowerHelper.Poison, move.amount).addCallback((po) -> {
                        move.getActions().loseHP(owner, enemy, GameUtilities.getPowerAmount(enemy, PoisonPower.POWER_ID), AbstractGameAction.AttackEffect.POISON);
                    });
                }
            }
            return originalValue;
        }
    }
}

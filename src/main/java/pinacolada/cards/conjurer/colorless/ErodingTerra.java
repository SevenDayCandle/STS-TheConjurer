package pinacolada.cards.conjurer.colorless;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import extendedui.EUIUtils;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.interfaces.subscribers.OnOrbPassiveEffectSubscriber;
import pinacolada.misc.CombatManager;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.orbs.pcl.Earth;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.skills.PTrigger;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.GameUtilities;

public class ErodingTerra extends PCLCard
{
    public static final PCLCardData DATA = register(ErodingTerra.class, ConjurerResources.conjurer)
            .setPower(3, CardRarity.SPECIAL)
            .setAffinities(2, PCLAffinity.Orange)
            .setUTags(PCLCardTag.Retain)
            .setColorless();

    public ErodingTerra()
    {
        super(DATA);
    }

    public void action(PSpecialSkill move, PCLUseInfo info)
    {
        PCLActions.bottom.applyPower(new ErodingTerraPower(info.source, move));
    }

    public void setup(Object input)
    {
        addSpecialMove(0, this::action, 1, 3);
        addGainPower(PTrigger.interactable(PCond.payEnergy(1), PMove.channelOrb(1, PCLOrbHelper.Earth)));
    }

    public static class ErodingTerraPower extends PSpecialCardPower implements OnOrbPassiveEffectSubscriber
    {
        public ErodingTerraPower(AbstractCreature owner, PSkill move)
        {
            super(ErodingTerra.DATA, owner, move);
        }

        @Override
        public void onOrbPassiveEffect(AbstractOrb o)
        {
            if (o instanceof Earth) {
                PCLActions.bottom.gainBlock(o.passiveAmount);
            }
        }

        public void onEvokeOrb(AbstractOrb o)
        {
            super.onEvokeOrb(o);
            if (o instanceof Earth) {
                PCLActions.bottom.gainBlock(o.evokeAmount);
            }
        }

        public void atEndOfTurn(boolean isPlayer)
        {
            super.atEndOfTurn(isPlayer);
            int total = EUIUtils.sumInt(GameUtilities.getEnemies(true),
                    e -> e.powers == null ? 0 : EUIUtils.sumInt(e.powers, p -> CombatManager.playerSystem.getElementButton(PCLAffinity.Orange).matchesPower(p.ID) ? p.amount : 0)) / Math.max(1,move.amount);
            if (total > 0) {
                PCLActions.bottom.applyPower(PCLCardTarget.Self, PCLPowerHelper.PlatedArmor, total);
                flash();
            }
        }


        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatManager.onOrbPassiveEffect.subscribe(this);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatManager.onOrbPassiveEffect.unsubscribe(this);
        }
    }
}

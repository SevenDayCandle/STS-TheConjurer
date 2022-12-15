package pinacolada.cards.conjurer.colorless;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLUseInfo;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.effects.AttackEffects;
import pinacolada.effects.vfx.ScreenOnFireEffect3;
import pinacolada.interfaces.subscribers.OnOrbApplyFocusSubscriber;
import pinacolada.misc.CombatManager;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.orbs.pcl.Fire;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.powers.conjurer.BurningPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.skills.PTrigger;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.GameUtilities;

public class BlazingHeat extends PCLCard
{
    public static final PCLCardData DATA = register(BlazingHeat.class, ConjurerResources.conjurer)
            .setPower(3, CardRarity.SPECIAL)
            .setAffinities(2, PCLAffinity.Red)
            .setUTags(PCLCardTag.Retain)
            .setColorless();

    public BlazingHeat()
    {
        super(DATA);
    }

    public void action(PSpecialSkill move, PCLUseInfo info)
    {
        PCLActions.bottom.playVFX(new ScreenOnFireEffect3());
        PCLActions.bottom.applyPower(new BlazingHeatPower(info.source, move));
    }

    public void setup(Object input)
    {
        addSpecialMove(0, this::action, 100, 2);
        addGainPower(PTrigger.interactable(PCond.payEnergy(1), PMove.channelOrb(1, PCLOrbHelper.Fire)));
    }

    public static class BlazingHeatPower extends PSpecialCardPower implements OnOrbApplyFocusSubscriber
    {

        public BlazingHeatPower(AbstractCreature owner, PSkill move)
        {
            super(BlazingHeat.DATA, owner, move);
        }

        @Override
        public void onApplyFocus(AbstractOrb orb)
        {
            if (orb instanceof Fire)
            {
                ((Fire) orb).subMultiplier = 0.5f * ((100 + move.amount) / 100f);
            }
        }

        public void atEndOfTurn(boolean isPlayer)
        {
            super.atEndOfTurn(isPlayer);
            for (AbstractMonster mo : GameUtilities.getEnemies(true))
            {
                BurningPower po = GameUtilities.getPower(mo, BurningPower.class);
                if (po != null)
                {
                    PCLActions.bottom.dealDamage(owner, mo, po.amount * move.extra, DamageInfo.DamageType.HP_LOSS, AttackEffects.BURN);
                }
            }
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatManager.onOrbApplyFocus.subscribe(this);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();
            CombatManager.onOrbApplyFocus.unsubscribe(this);
            for (AbstractOrb orb : player.orbs)
            {
                if (orb instanceof Fire)
                {
                    ((Fire) orb).subMultiplier = 0.5f;
                }
            }
        }
    }
}

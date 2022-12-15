package pinacolada.cards.conjurer.colorless;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLUseInfo;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.effects.vfx.ScreenFreezingEffect;
import pinacolada.effects.vfx.SnowballEffect;
import pinacolada.interfaces.subscribers.OnOrbApplyFocusSubscriber;
import pinacolada.misc.CombatManager;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.orbs.pcl.Water;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.powers.conjurer.ChilledPower;
import pinacolada.powers.conjurer.FrozenPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.skills.PTrigger;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.GameUtilities;

public class SheerCold extends PCLCard
{
    public static final PCLCardData DATA = register(SheerCold.class, ConjurerResources.conjurer)
            .setPower(3, CardRarity.SPECIAL)
            .setAffinities(2, PCLAffinity.Blue)
            .setUTags(PCLCardTag.Retain)
            .setColorless();

    public SheerCold()
    {
        super(DATA);
    }

    public void action(PSpecialSkill move, PCLUseInfo info)
    {
        PCLActions.bottom.playVFX(new ScreenFreezingEffect());
        PCLActions.bottom.applyPower(new SheerColdPower(info.source, move));
    }

    public void setup(Object input)
    {
        addSpecialMove(0, this::action, 12, 1);
        addGainPower(PTrigger.interactable(PCond.payEnergy(1), PMove.channelOrb(1, PCLOrbHelper.Water)));
    }

    public static class SheerColdPower extends PSpecialCardPower implements OnOrbApplyFocusSubscriber
    {

        public SheerColdPower(AbstractCreature owner, PSkill move)
        {
            super(SheerCold.DATA, owner, move);
        }

        @Override
        public void onInitialApplication() {
            super.onInitialApplication();

            CombatManager.onOrbApplyFocus.subscribe(this);
        }

        @Override
        public void onRemove() {
            super.onRemove();

            CombatManager.onOrbApplyFocus.unsubscribe(this);
        }

        @Override
        public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source)
        {
            super.onApplyPower(power, target, source);

            if (target != owner && power.ID.equals(ChilledPower.POWER_ID) && GameUtilities.getPowerAmount(target, ChilledPower.POWER_ID) >= move.amount)
            {
                PCLActions.top.playVFX(new SnowballEffect(owner.hb.cX, owner.hb.cY, target.hb.cX, target.hb.cY)
                        .setColor(Color.NAVY, Color.NAVY).setRealtime(true));
                PCLActions.bottom.applyPower(source, new FrozenPower(target, move.extra));
            }
        }

        @Override
        public void onApplyFocus(AbstractOrb orb) {
            int index = player.orbs.indexOf(orb);
            if (index >= 0) {
                if (index + 1 < player.filledOrbCount() && Water.ORB_ID.equals(player.orbs.get(index + 1).ID)) {
                    GameUtilities.modifyOrbTemporaryFocus(orb, player.orbs.get(index + 1).passiveAmount, true, false);
                }
                if (index - 1 >= 0 && Water.ORB_ID.equals(player.orbs.get(index - 1).ID)) {
                    GameUtilities.modifyOrbTemporaryFocus(orb, player.orbs.get(index - 1).passiveAmount, true, false);
                }
            }
        }
    }
}

package pinacolada.cards.conjurer.colorless;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import extendedui.EUIUtils;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLUseInfo;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.interfaces.subscribers.OnOrbPassiveEffectSubscriber;
import pinacolada.misc.CombatManager;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.orbs.pcl.Air;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.skills.PTrigger;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.GameUtilities;

public class VerdantGale extends PCLCard
{
    public static final PCLCardData DATA = register(VerdantGale.class, ConjurerResources.conjurer)
            .setPower(3, CardRarity.SPECIAL)
            .setAffinities(2, PCLAffinity.Green)
            .setUTags(PCLCardTag.Retain)
            .setColorless();

    public VerdantGale()
    {
        super(DATA);
    }

    public void action(PSpecialSkill move, PCLUseInfo info)
    {
        PCLActions.bottom.applyPower(new VerdantGalePower(info.source, move));
    }

    public void setup(Object input)
    {
        addSpecialMove(0, this::action, 1, 3);
        addGainPower(PTrigger.interactable(PCond.payEnergy(1), PMove.channelOrb(1, PCLOrbHelper.Air)));
    }

    public static class VerdantGalePower extends PSpecialCardPower implements OnOrbPassiveEffectSubscriber
    {

        public VerdantGalePower(AbstractCreature owner, PSkill move)
        {
            super(VerdantGale.DATA, owner, move);
        }

        @Override
        public void onOrbPassiveEffect(AbstractOrb o)
        {
            doAction(o);
        }

        public void onEvokeOrb(AbstractOrb o)
        {
            super.onEvokeOrb(o);
            doAction(o);
        }

        public void atEndOfTurn(boolean isPlayer)
        {
            super.atEndOfTurn(isPlayer);
            int total = EUIUtils.sumInt(GameUtilities.getEnemies(true),
                    e -> e.powers == null ? 0 : EUIUtils.sumInt(e.powers, p -> CombatManager.playerSystem.getElementButton(PCLAffinity.Green).matchesPower(p.ID) ? p.amount : 0)) / Math.max(1,move.amount);
            if (total > 0) {
                PCLActions.bottom.drawNextTurn(total);
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

        protected void doAction(AbstractOrb o)
        {
            if (o instanceof Air) {
                for (AbstractMonster mo : GameUtilities.getEnemies(true)) {
                    for (AbstractPower po : mo.powers) {
                        if (GameUtilities.isCommonDebuff(po)) {
                            PCLActions.bottom.spreadPower(player, mo, po.ID, move.amount);
                        }
                    }
                }
            }
        }
    }
}

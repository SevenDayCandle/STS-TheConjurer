package pinacolada.cards.conjurer.colorless;

import com.evacipated.cardcrawl.mod.stslib.actions.defect.EvokeSpecificOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLUseInfo;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.effects.AttackEffects;
import pinacolada.effects.SFX;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.skills.PTrigger;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.skills.skills.base.conditions.PCond_TakeDamage;
import pinacolada.utilities.GameUtilities;

import java.util.ArrayList;

public class KokomiSangonomiya extends PCLCard
{
    public static final PCLCardData DATA = register(KokomiSangonomiya.class, ConjurerResources.conjurer)
            .setPower(2, CardRarity.RARE)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Light)
            .setRTags(PCLCardTag.Ethereal)
            .setMaxCopies(1)
            .setColorless()
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public KokomiSangonomiya()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addSpecialMove(0, this::action);
        addGainPower(PTrigger.interactable(new PCond_TakeDamage(8).setUpgrade(-1), PMove.channelOrb(1, PCLOrbHelper.Dark).setExtra(2)));
    }

    public void action(PSpecialSkill move, PCLUseInfo info)
    {
        PCLActions.bottom.applyPower(new KokomiSangonomiyaPower(info.source, move));
    }

    public static class KokomiSangonomiyaPower extends PSpecialCardPower
    {

        public KokomiSangonomiyaPower(AbstractCreature owner, PSkill move)
        {
            super(KokomiSangonomiya.DATA, owner, move);

            this.priority = -99;
        }

        private int absorbDamage(int damage, ArrayList<AbstractOrb> waterOrbs)
        {
            final AbstractOrb next = waterOrbs.get(0);
            final float temp = damage;

            damage = Math.max(0, damage - next.evokeAmount);

            if (GameUtilities.canOrbApplyFocus(next))
            {
                next.evokeAmount -= temp;
                if (next.evokeAmount <= 0)
                {
                    waterOrbs.remove(next);
                    next.evokeAmount = 0;
                    PCLActions.top.add(new EvokeSpecificOrbAction(next));
                }
            }
            else
            {
                PCLActions.top.add(new EvokeSpecificOrbAction(next));
            }

            return (damage > 0 && waterOrbs.size() > 0) ? absorbDamage(damage, waterOrbs) : damage;
        }

        @Override
        public int onAttackedToChangeDamage(DamageInfo info, int damageAmount)
        {
            int newDamage = damageAmount;
            final ArrayList<AbstractOrb> waterOrbs = new ArrayList<>();
            for (AbstractOrb orb : player.orbs)
            {
                if (orb.evokeAmount > 0)
                {
                    waterOrbs.add(orb);
                }
            }

            if (waterOrbs.size() > 0)
            {
                if (damageAmount > 0)
                {
                    newDamage = absorbDamage(damageAmount, waterOrbs);
                }

                if (info.owner != null && info.owner.isPlayer != owner.isPlayer)
                {
                    PCLActions.bottom.dealDamage(owner, info.owner, damageAmount - newDamage, DamageInfo.DamageType.THORNS, AttackEffects.WATER);
                    flashWithoutSound();
                }
                else if (owner.isPlayer)
                {
                    int[] damageMatrix = DamageInfo.createDamageMatrix(damageAmount - newDamage, false);
                    PCLActions.bottom.dealDamageToAll(damageMatrix, DamageInfo.DamageType.THORNS, AttackEffects.WATER);
                    flashWithoutSound();
                }
            }

            return super.onAttackedToChangeDamage(info, newDamage);
        }

        @Override
        public float modifyBlock(float blockAmount)
        {
            return 0f;
        }

        @Override
        public float modifyBlock(float blockAmount, AbstractCard card)
        {
            return 0f;
        }

        @Override
        public float modifyBlockLast(float blockAmount)
        {
            return 0f;
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            PCLActions.bottom.playSFX(SFX.PCL_WATER_DOME);
        }
    }
}

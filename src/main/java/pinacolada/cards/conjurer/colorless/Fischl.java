package pinacolada.cards.conjurer.colorless;

import com.megacrit.cardcrawl.orbs.AbstractOrb;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.*;
import pinacolada.effects.AttackEffects;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PCustomCond;
import pinacolada.utilities.GameUtilities;

public class Fischl extends PCLCard
{
    public static final PCLCardData DATA = register(Fischl.class, ConjurerResources.conjurer)
            .setAttack(0, CardRarity.UNCOMMON, PCLAttackType.Magical, PCLCardTarget.RandomEnemy)
            .setDamage(1, 3)
            .setAffinities(PCLAffinity.Dark)
            .setColorless()
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Fischl()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.DARK);
        addUseMove(new FischlCond(DATA), PMove.channelOrb(1, PCLOrbHelper.Lightning, PCLOrbHelper.Dark).setAlt(true));
        addUseMove(PCond.limited(), PCond.checkOrb(1, PCLOrbHelper.Lightning, PCLOrbHelper.Dark), PMove.obtainDiscardPile(1, Fischl_Oz.DATA));
    }

    protected static class FischlCond extends PCustomCond
    {
        public FischlCond(PCLCardData data)
        {
            super(data);
        }

        public boolean checkCondition(PCLUseInfo info, boolean isUsing, boolean fromTrigger)
        {
            return GameUtilities.getOrbCount() == 0;
        }

        protected void useImpl(PCLUseInfo info)
        {
            PCLActions.bottom.evokeOrb(amount).addCallback(orbs -> {
               for (AbstractOrb orb : orbs)
               {
                   PCLActions.bottom.channelOrb(orb);
               }
            });
        }
    }
}

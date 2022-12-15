package pinacolada.cards.conjurer.series.genshinimpact;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.orbs.pcl.Water;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PCustomCond;
import pinacolada.utilities.GameUtilities;

public class Ganyu extends PCLCard
{
    public static final PCLCardData DATA = register(Ganyu.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.RARE, PCLAttackType.Ranged)
            .setDamage(10, 2)
            .setTags(PCLCardTag.Exhaust)
            .setRTags(PCLCardTag.Ethereal)
            .setAffinities(2, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Ganyu()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove().setDamageEffect(PCLEffekseerEFX.SPEAR03).setVFXColor(Color.SKY, Color.SKY);
        addUseMove(new GanyuCond(DATA), PMove.channelOrb(2, PCLOrbHelper.Water));
        addUseMove(PCond.checkLevel(2, PCLAffinity.Blue), PMove.gain(2, PCLPowerHelper.Blur));
    }

    protected static class GanyuCond extends PCustomCond
    {
        public GanyuCond(PCLCardData data)
        {
            super(data);
        }

        public boolean checkCondition(PCLUseInfo info, boolean isUsing, boolean fromTrigger)
        {
            return info.target != null && GameUtilities.getOrbCount(PCLOrbHelper.Water) == 0;
        }

        protected void useImpl(PCLUseInfo info)
        {
            if (info.target != null)
            {
                for (AbstractOrb orb : player.orbs)
                {
                    if (Water.ORB_ID.equals(orb.ID))
                    {
                        GameUtilities.modifyOrbBaseFocus(orb, orb.passiveAmount, true, false);
                    }
                }
            }
        }
    }
}

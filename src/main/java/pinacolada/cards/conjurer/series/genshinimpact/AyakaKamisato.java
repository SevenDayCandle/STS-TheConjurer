package pinacolada.cards.conjurer.series.genshinimpact;


import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.misc.PCLUseInfo;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.conjurer.FrostbitePower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.skills.PMultiCond;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.skills.skills.special.conditions.PCond_OnAllyDeath;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class AyakaKamisato extends PCLCard
{
    public static final PCLCardData DATA = register(AyakaKamisato.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Normal, PCLCardTarget.RandomEnemy)
            .setRTags(PCLCardTag.Ethereal)
            .setDamage(12, 0, 0)
            .setPriority(1)
            .setHp(4, 1)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public AyakaKamisato()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.ICE);
        addUseMove(PMultiCond.or(PCond.onSummon(), new PCond_OnAllyDeath()), getSpecialMove(0, this::specialMove, 2).setUpgrade(0, 1).setTarget(PCLCardTarget.Single));
    }

    public void specialMove(PSpecialSkill move, PCLUseInfo info)
    {
        int amount = 0;
        for (AbstractCreature c : GameUtilities.getAllCharacters(true))
        {
            FrostbitePower frost = GameUtilities.getPower(c, FrostbitePower.class);
            if (frost != null)
            {
                amount += frost.amount;
                move.getActions().removePower(c, c, FrostbitePower.POWER_ID);
            }
        }

        if (amount > 0)
        {
            move.getActions().loseHP(amount, PCLAttackVFX.CLAW.key);
            move.getActions().gain(PCLPowerHelper.Strength, amount).setTemporary(true);
        }
    }
}

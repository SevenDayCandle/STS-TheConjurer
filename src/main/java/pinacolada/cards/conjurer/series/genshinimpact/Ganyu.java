package pinacolada.cards.conjurer.series.genshinimpact;


import extendedui.EUIUtils;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.misc.PCLUseInfo;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.ui.combat.ConjurerReactionMeter;

@VisibleCard
public class Ganyu extends PCLCard
{
    public static final PCLCardData DATA = register(Ganyu.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Ranged)
            .setRTags(PCLCardTag.Ethereal)
            .setDamage(6, 0)
            .setPriority(1)
            .setHp(2, 1)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Ganyu()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.ICE);
        addUseMove(PCond.cooldown(1), getSpecialMove(0, this::specialMove, 1));
    }

    public void specialMove(PSpecialSkill move, PCLUseInfo info)
    {
        if (info.target != null)
        {
            int amount = EUIUtils.sumInt(info.target.powers, po -> ConjurerReactionMeter.meter.isPowerElemental(po.ID, PCLAffinity.Blue) ? po.amount : 0) + move.amount;
            PCLActions.bottom.applyPower(info.target, PCLCardTarget.Single, PCLElementHelper.Gelus, amount);
        }
    }
}

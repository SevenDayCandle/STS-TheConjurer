package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.cards.conjurer.curse.Curse_Decay;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

@VisibleCard
public class Xiao extends PCLCard
{
    public static final PCLCardData DATA = register(Xiao.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.RARE, PCLAttackType.Piercing, PCLCardTarget.AllEnemy)
            .setRTags(PCLCardTag.Ethereal)
            .setDamage(3, 0, 2)
            .setPriority(1)
            .setHp(5, 1)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Xiao()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.POISON);
        addUseMove(PCond.cooldown(1), PMultiSkill.join(PMove.obtainDrawPile(1, Curse_Decay.DATA), PMove.applyToEnemies(6, PCLPowerHelper.Poison)));
    }
}

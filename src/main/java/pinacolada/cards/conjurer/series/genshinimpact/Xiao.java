package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.pcl.curse.Curse_Decay;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

@VisibleCard
public class Xiao extends PCLCard {
    public static final PCLCardData DATA = register(Xiao.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Piercing, PCLCardTarget.AllEnemy)
            .setDamage(2, 1, 2)
            .setHp(5, 1)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Xiao() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.POISON);
        addUseMove(PCond.cooldown(1), PMultiSkill.join(PMove.createDrawPile(1, Curse_Decay.DATA.ID), PMove.applyToEnemies(6, PCLPowerHelper.Poison)));
    }
}

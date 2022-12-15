package pinacolada.cards.conjurer.series.genshinimpact;

import pinacolada.actions.PCLActions;
import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.effects.AttackEffects;
import pinacolada.effects.VFX;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class Klee extends PCLCard
{
    public static final PCLCardData DATA = register(Klee.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON, PCLAttackType.Magical, PCLCardTarget.AllEnemy)
            .setDamage(2, 0)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Light)
            .setTags(
                    PCLCardTag.Exhaust.make(1, array(0, 1))
            )
            .setMultiformData(2)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Klee()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.SMALL_EXPLOSION);
        addUseMove(PMove.obtainDrawPile(2, Klee_JumpyDumpty.DATA).setUpgrade(0, 1));
        addUseMove(PCond.combust(), PMove.channelOrb(1, PCLOrbHelper.Fire));
    }

    @Override
    public void onPreUse(PCLUseInfo info)
    {
        if (info.source != null)
        {
            PCLActions.bottom.playVFX(VFX.flameBarrier(info.source.hb));
            PCLActions.bottom.playVFX(VFX.smallExplosion(info.source.hb));
        }
    }
}

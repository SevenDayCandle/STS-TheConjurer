package pinacolada.cards.conjurer.series.shinmegamitensei;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLAttackType;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrait;
import pinacolada.skills.conjurer.modifiers.PMod_PerReaction;
import pinacolada.skills.skills.base.modifiers.PMod_PerOrb;

public class Amon extends PCLCard
{
    public static final PCLCardData DATA = register(Amon.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON, PCLAttackType.Piercing)
            .setDamage(10, 2)
            .setAffinities(2, PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Amon()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.CLAW);
        addUseMove(new PMod_PerOrb(1, PCLOrbHelper.Fire), PTrait.hasDamage(5).setUpgrade(1));
        addUseMove(PCond.redox(), new PMod_PerReaction(4), PMove.triggerOrb(1, 0, PCLOrbHelper.Fire));
    }
}

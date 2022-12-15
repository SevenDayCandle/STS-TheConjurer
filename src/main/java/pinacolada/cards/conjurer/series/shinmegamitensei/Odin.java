package pinacolada.cards.conjurer.series.shinmegamitensei;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLAttackType;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.conjurer.moves.PMove_GainReaction;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.base.modifiers.PMod_PerDamage;

public class Odin extends PCLCard
{
    public static final PCLCardData DATA = register(Odin.class, ConjurerResources.conjurer)
            .setAttack(3, CardRarity.RARE, PCLAttackType.Piercing)
            .setDamage(13, 1)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Odin()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove().setDamageEffect(PCLEffekseerEFX.SPEAR04);
        addUseMove(new PMod_PerDamage(3).setUpgrade(-1), new PMove_GainReaction(1));
        addUseMove(PCond.redox(), PMultiSkill.choose(1, PMove.applyToSingle(2, PCLPowerHelper.Weak), PMove.applyToSingle(2, PCLPowerHelper.Vulnerable)));
    }
}

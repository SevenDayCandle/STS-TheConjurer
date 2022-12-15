package pinacolada.cards.conjurer.series.shinmegamitensei;

import com.badlogic.gdx.graphics.Color;
import pinacolada.cards.base.*;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.conjurer.moves.PMove_GainReaction;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.base.conditions.PCond_CheckOrb;
import pinacolada.skills.skills.base.modifiers.PMod_PerPower;
import pinacolada.skills.skills.base.moves.PMove_EvokeOrb;

public class BlackFrost extends PCLCard
{
    public static final PCLCardData DATA = register(BlackFrost.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON, PCLAttackType.Magical)
            .setDamage(10, array(3, 0))
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue, PCLAffinity.Dark)
            .setMultiformData(2)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public BlackFrost()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove().setDamageEffect(PCLEffekseerEFX.FIRE07, Color.PURPLE, 1f);
        addUseMove(new PMod_PerPower(PCLCardTarget.Single, 1, PCLPowerHelper.Chilled, PCLPowerHelper.Burning), new PMove_GainReaction(1).setUpgrade(0, 1));
        addUseMove(new PCond_CheckOrb(1, PCLOrbHelper.Water), PMultiSkill.join(PMove.selfExhaust(), new PMove_EvokeOrb(2, 0, PCLOrbHelper.Water)));
    }
}

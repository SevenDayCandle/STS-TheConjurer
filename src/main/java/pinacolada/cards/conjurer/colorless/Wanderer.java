package pinacolada.cards.conjurer.colorless;

import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrait;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.base.modifiers.PMod_PerDistinctPower;

public class Wanderer extends PCLCard
{
    public static final PCLCardData DATA = register(Wanderer.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.RARE, PCLAttackType.Piercing, PCLCardTarget.RandomEnemy)
            .setDamage(1, 0, 3, 1)
            .setTags(PCLCardTag.Ethereal)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Dark)
            .setColorless()
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Wanderer()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove().setDamageEffect(PCLEffekseerEFX.SWORD04);
        addUseMove(new PMod_PerDistinctPower(PCLCardTarget.AllEnemy, 1), PTrait.hasDamage(1));
        addUseMove(PCond.semiLimited(), PCond.onDraw(), PMultiSkill.join(PMove.gain(5, PCLPowerHelper.DelayedDamage), PMove.gainTemporary(1, PCLPowerHelper.ThousandCuts)));
    }
}

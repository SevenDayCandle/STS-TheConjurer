package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.*;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.modifiers.PMod_IncreaseOnUse;
import pinacolada.skills.skills.base.modifiers.PMod_PerCreatureWith;
import pinacolada.skills.skills.base.traits.PTrait_Damage;

public class Tartaglia extends PCLCard
{
    public static final PCLCardData DATA = register(Tartaglia.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON, PCLAttackType.Ranged, PCLCardTarget.AllEnemy)
            .setDamage(6, 1, 2)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Tartaglia()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove().setDamageEffect(PCLEffekseerEFX.SWORD15);
        addUseMove(new PMod_IncreaseOnUse(1), PMove.applyToEveryone(2, PCLPowerHelper.Chilled).setUpgrade(1));
        addUseMove(new PMod_PerCreatureWith(1, PCLPowerHelper.Burning), new PTrait_Damage(3));
    }
}

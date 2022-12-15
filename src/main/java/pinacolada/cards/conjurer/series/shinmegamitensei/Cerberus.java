package pinacolada.cards.conjurer.series.shinmegamitensei;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.conjurer.moves.PMove_GainReaction;
import pinacolada.skills.skills.base.modifiers.PMod_PerCreatureWith;
import pinacolada.skills.skills.base.moves.PMove_ModifyDamage;

public class Cerberus extends PCLCard
{
    public static final PCLCardData DATA = register(Cerberus.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON)
            .setDamage(14, 2)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Cerberus()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.BITE);
        addUseMove(new PMod_PerCreatureWith(1, PCLPowerHelper.Burning, PCLPowerHelper.Weak), new PMove_ModifyDamage(1, 4).setUpgrade(2));
        addUseMove(PCond.onExhaust(), new PMod_PerCreatureWith(1, PCLPowerHelper.Burning), new PMove_GainReaction(8));
    }
}

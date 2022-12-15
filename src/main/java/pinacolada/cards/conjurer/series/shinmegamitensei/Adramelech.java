package pinacolada.cards.conjurer.series.shinmegamitensei;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PTrait;
import pinacolada.skills.conjurer.modifiers.PMod_PerReaction;
import pinacolada.skills.skills.base.moves.PMove_AddLevel;

public class Adramelech extends PCLCard
{
    public static final PCLCardData DATA = register(Adramelech.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON)
            .setDamage(8, array(3, 1))
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green, PCLAffinity.Orange)
            .setMultiformData(2)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Adramelech()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.FIRE);
        addUseMove(new PMod_PerReaction(4).setUpgrade(0, -1), PTrait.hasDamage(1));
        addUseMove(PCond.limited(), PCond.onExhaust(), new PMove_AddLevel(1));
    }
}

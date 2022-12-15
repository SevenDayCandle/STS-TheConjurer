package pinacolada.cards.conjurer.series.persona;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.base.modifiers.PMod_ScryPerCard;

public class HaruOkumura extends PCLCard
{
    public static final PCLCardData DATA = register(HaruOkumura.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE)
            .setMagicNumber(5, 1)
            .setAffinities(PCLAffinity.Orange, PCLAffinity.Light)
            .setLoadout(ConjurerPlayerData.persona);

    public HaruOkumura()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PCond.cooldown(2), PMove.selfTransform(HaruOkumura_Milady.DATA));
        addUseMove(new PMod_ScryPerCard(2).setUpgrade(1), PMultiSkill.join(PMove.motivate(2).useParent(true), PMove.applyToRandom(2, PCLPowerHelper.Stoned)));
    }
}

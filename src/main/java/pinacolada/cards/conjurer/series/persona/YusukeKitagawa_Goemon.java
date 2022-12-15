package pinacolada.cards.conjurer.series.persona;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

public class YusukeKitagawa_Goemon extends PCLCard
{
    public static final PCLCardData DATA = register(YusukeKitagawa_Goemon.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.SPECIAL)
            .setAffinities(2, PCLAffinity.Blue)
            .setTags(PCLCardTag.Retain, PCLCardTag.Exhaust)
            .setLoadout(ConjurerPlayerData.persona);

    public YusukeKitagawa_Goemon()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.channelOrb(3, PCLOrbHelper.Water).setUpgradeExtra(1));
        addUseMove(PMultiSkill.choose(
                PMove.obtain(YusukeKitagawa.DATA),
                PMove.applyToEnemies(3, PCLPowerHelper.Weak, PCLPowerHelper.Vulnerable)
        ));
    }
}

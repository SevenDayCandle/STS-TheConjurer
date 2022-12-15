package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class Xiangling_Guoba extends PCLCard
{
    public static final PCLCardData DATA = register(Xiangling_Guoba.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.SPECIAL, PCLCardTarget.AllEnemy)
            .setAffinities(PCLAffinity.Red)
            .setTags(PCLCardTag.Exhaust.make(), PCLCardTag.Haste.make(1, -1))
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Xiangling_Guoba()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.channelOrb(1, PCLOrbHelper.Fire).setExtra(1));
        addUseMove(PCond.onDraw(), PMove.applyToEnemies(1, PCLPowerHelper.Burning));
    }
}

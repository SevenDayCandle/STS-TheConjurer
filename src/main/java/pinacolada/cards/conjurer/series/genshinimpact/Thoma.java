package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.modifiers.PMod_PerDistinctPower;

public class Thoma extends PCLCard
{
    public static final PCLCardData DATA = register(Thoma.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON, PCLCardTarget.Single)
            .setBlock(8, 3)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Thoma()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PCond.onDraw(), PMove.gainBlock(3).setUpgrade(1));
        addUseMove(PCond.onExhaust(), new PMod_PerDistinctPower(PCLCardTarget.Self,1), PMove.gainTempHP(2));
    }
}

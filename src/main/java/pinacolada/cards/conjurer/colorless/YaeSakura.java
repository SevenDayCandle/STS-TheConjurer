package pinacolada.cards.conjurer.colorless;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.modifiers.PMod_EvokePerOrb;

public class YaeSakura extends PCLCard
{
    public static final PCLCardData DATA = register(YaeSakura.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE, PCLCardTarget.AllEnemy)
            .setBlock(1, 0)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Dark)
            .setTags(PCLCardTag.Exhaust)
            .setRTags(PCLCardTag.Ethereal)
            .setColorless();

    public YaeSakura()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.applyToEnemies(2, PCLPowerHelper.Silenced));
        addUseMove(new PMod_EvokePerOrb(1), PMove.apply(PCLCardTarget.All, 1, PCLPowerHelper.Blinded));
    }
}

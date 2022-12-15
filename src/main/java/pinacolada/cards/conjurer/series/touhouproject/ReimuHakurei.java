package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLAttackType;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.modifiers.PMod_BonusPerAffinityLevel;

public class ReimuHakurei extends PCLCard
{
    public static final PCLCardData DATA = register(ReimuHakurei.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON, PCLAttackType.Ranged)
            .setDamage(3, 1, 2)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public ReimuHakurei()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.BLUNT_LIGHT);
        addUseMove(PMove.applyToSingle(1, PCLPowerHelper.Weak).setUpgrade(1));
        addUseMove(PCond.redox(), new PMod_BonusPerAffinityLevel(1, PCLAffinity.Orange), PMove.scry(1));
    }
}

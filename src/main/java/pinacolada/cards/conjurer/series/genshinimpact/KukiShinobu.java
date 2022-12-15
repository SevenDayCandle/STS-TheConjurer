package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.modifiers.PMod_DiscardPerCard;

public class KukiShinobu extends PCLCard
{
    public static final PCLCardData DATA = register(KukiShinobu.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.UNCOMMON, PCLAttackType.Piercing)
            .setDamage(2, 0, 2)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setUTags(PCLCardTag.Haste)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public KukiShinobu()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.DAGGER);
        addUseMove(new PMod_DiscardPerCard(2, PCLCardGroupHelper.Hand), PMove.applyToRandom(4, PCLPowerHelper.Poison).setUpgrade(1));
        addUseMove(PCond.onDiscard(), new PMove_ObtainThrowingKnife(1));
    }
}

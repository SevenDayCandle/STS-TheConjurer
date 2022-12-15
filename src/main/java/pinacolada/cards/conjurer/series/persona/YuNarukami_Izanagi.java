package pinacolada.cards.conjurer.series.persona;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.moves.PMove_RemovePower;

public class YuNarukami_Izanagi extends PCLCard
{
    public static final PCLCardData DATA = register(YuNarukami_Izanagi.class, ConjurerResources.conjurer)
            .setAttack(4, CardRarity.SPECIAL)
            .setDamage(14, 4)
            .setAffinities(PCLAffinity.Star)
            .setTags(
                    PCLCardTag.Exhaust.make(),
                    PCLCardTag.Retain.make(-1)
            )
            .setLoadout(ConjurerPlayerData.persona);

    public YuNarukami_Izanagi()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove().setDamageEffect(PCLEffekseerEFX.SWORD22);
        addUseMove(new PMove_RemovePower(PCLCardTarget.All, PCLPowerHelper.Strength));
        addUseMove(PCond.onTurnStart(), PMove.applyToEveryone(1, PCLPowerHelper.Shackles));
    }
}

package pinacolada.cards.conjurer.colorless;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrigger;
import pinacolada.skills.skills.base.conditions.PCond_Shuffle;

public class Fischl_Oz extends PCLCard
{
    public static final PCLCardData DATA = register(Fischl_Oz.class, ConjurerResources.conjurer)
            .setPower(2, CardRarity.SPECIAL)
            .setRTags(PCLCardTag.Ethereal)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Dark)
            .setColorless()
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Fischl_Oz()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.gain(3, PCLPowerHelper.Sorcery), PMove.gainOrbSlots(1));
        addGainPower(PTrigger.when(new PCond_Shuffle(), PMove.gainOrbSlots(1)));
    }
}

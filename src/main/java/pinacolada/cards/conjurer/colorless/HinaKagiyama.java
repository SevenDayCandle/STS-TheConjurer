package pinacolada.cards.conjurer.colorless;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.cards.pcl.replacement.Miracle;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrigger;

public class HinaKagiyama extends PCLCard
{
    public static final PCLCardData DATA = register(HinaKagiyama.class, ConjurerResources.conjurer)
            .setPower(2, CardRarity.SPECIAL)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Light)
            .setUTags(PCLCardTag.Innate, PCLCardTag.Retain)
            .setColorless()
            .setLoadout(ConjurerPlayerData.touhouProject);

    public HinaKagiyama()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addGainPower(PTrigger.when(PCond.onDraw().setCardTypes(CardType.CURSE), PMove.draw(1)).setAmount(1),
                PTrigger.when(PCond.onTurnStart(), PCond.purge(1, PCLCardGroupHelper.ExhaustPile).setCardTypes(CardType.CURSE), PMove.obtain(Miracle.DATA)));
    }

}

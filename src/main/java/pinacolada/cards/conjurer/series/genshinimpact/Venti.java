package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.cards.conjurer.colorless.VerdantGale;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.base.modifiers.PMod_CycleBranch;

public class Venti extends PCLCard
{
    public static final PCLCardData DATA = register(Venti.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.RARE)
            .setBlock(2, 0)
            .setAffinities(PCLAffinity.Star, PCLAffinity.Green)
            .setTags(PCLCardTag.Ethereal.make(1, array(0, 1, 1)))
            .setMultiformData(3)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Venti()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(new PMod_CycleBranch(3).setUpgrade(0, 1, -1).setCardTypes(CardType.SKILL),
                PMultiSkill.join(PMove.channelOrb(1, PCLOrbHelper.Air).setUpgrade(0, 0, 1), PMove.gain(2, PCLPowerHelper.SupportDamage).setUpgrade(0, 0, 2)));
        addUseMove(PCond.limited(), PCond.checkLevel(6, PCLAffinity.Green), PMove.playCopy(1, PCLCardTarget.Self, VerdantGale.DATA.ID));
    }
}

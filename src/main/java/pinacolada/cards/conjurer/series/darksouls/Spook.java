package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Spook extends PCLCard {
    public static final PCLCardData DATA = register(Spook.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.UNCOMMON, PCLCardTarget.None)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Purple)
            .setTags(PCLCardTag.Ethereal)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public Spook() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.applyToEveryone(2, PCLPowerData.Blinded));
        addUseMove(PCond.onDraw(), PMove.applyToRandom(3, PCLPowerData.Blinded).setScope(1, 1));
    }
}

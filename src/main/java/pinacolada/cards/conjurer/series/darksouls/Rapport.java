package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class Rapport extends PCLCard {
    public static final PCLCardData DATA = register(Rapport.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.UNCOMMON, PCLCardTarget.Single)
            .setAffinities(PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public Rapport() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.apply(PCLCardTarget.Single, 2, PCLPowerData.Strength));
        addUseMove(PMove.apply(PCLCardTarget.Single, 7, PCLPowerData.Shackles).setUpgrade(2));
    }
}

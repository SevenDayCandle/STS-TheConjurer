package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class Rapport extends PCLCard {
    public static final PCLCardData DATA = register(Rapport.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Team)
            .setAffinities(PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.darkSouls, true);

    public Rapport() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.applyToTeam(2, PCLPowerHelper.Invigorated)).setUpgrade(1);
        addUseMove(PMove.triggerAlly(PCLCardTarget.RandomAlly, 1));
    }
}

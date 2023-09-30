package pinacolada.cards.conjurer.series.monsterhunter;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class VigorwaspSpray extends PCLCard {
    public static final PCLCardData DATA = register(VigorwaspSpray.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.UNCOMMON)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public VigorwaspSpray() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMod.perDistinctDebuff(PCLCardTarget.Single, 1), PMove.gain(3, PCLPowerData.Vigor).setUpgrade(1));
        addUseMove(PMove.heal(PCLCardTarget.Single, 4));
    }
}

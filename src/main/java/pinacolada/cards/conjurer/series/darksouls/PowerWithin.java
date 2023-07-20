package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class PowerWithin extends PCLCard {
    public static final PCLCardData DATA = register(PowerWithin.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.RARE, PCLCardTarget.Team)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public PowerWithin() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMod.perCreature(PCLCardTarget.AllAlly, 1), PMove.gainTemporary(3, PCLPowerHelper.Strength).setUpgrade(1));
        addUseMove(PMove.loseHp(PCLCardTarget.AllAlly, 7));
    }
}

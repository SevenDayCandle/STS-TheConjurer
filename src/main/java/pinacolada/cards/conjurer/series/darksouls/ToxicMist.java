package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.CMove;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class ToxicMist extends PCLCard {
    public static final PCLCardData DATA = register(ToxicMist.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.AllEnemy)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public ToxicMist() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.applyToEnemies(5, PCLPowerData.Poison).setUpgrade(2));
        addUseMove(CCond.react(), PMod.perPower(2, PCLPowerData.Poison).setTarget(PCLCardTarget.AllEnemy), CMove.gainMatter(1));
    }
}

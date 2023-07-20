package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class CarthusBeacon extends PCLCard {
    public static final PCLCardData DATA = register(CarthusBeacon.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Self)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.darkSouls, true);

    public CarthusBeacon() {
        super(DATA);
    }

    public void setup(Object input) {
        addGainPower(2, PTrigger.when(PCond.onOtherCardPlayed(CardType.SKILL, PCLEnum.CardType.SUMMON), PMove.gainTemporary(1, PCLPowerHelper.Strength).setUpgrade(1)));
    }
}

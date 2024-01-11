package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.powers.conjurer.VentusPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Seiryu extends PCLCard {
    public static final PCLCardData DATA = register(Seiryu.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.RARE, PCLAttackType.Piercing)
            .setDamage(7, array(1, 0))
            .setHp(12, array(2, 1))
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Seiryu() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.CLAW);
        addGainPower(PTrigger.interactable(CCond.payLevel(3, PCLAffinity.Green).setUpgrade(0, 1), PMultiSkill.choose(
                PMove.applyToEnemies(2, AquaPower.DATA).setUpgrade(1),
                PMove.applyToEnemies(2, VentusPower.DATA).setUpgrade(1)
        )));
    }
}

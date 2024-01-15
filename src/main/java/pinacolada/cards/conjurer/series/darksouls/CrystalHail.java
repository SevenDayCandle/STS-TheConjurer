package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.conjurer.CooledPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.*;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class CrystalHail extends PCLCard {
    public static final PCLCardData DATA = register(CrystalHail.class, ConjurerResources.conjurer)
            .setPower(2, CardRarity.UNCOMMON)
            .setCostUpgrades(-1)
            .setAffinities(2, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public CrystalHail() {
        super(DATA);
    }

    public void setup(Object input) {
        addGainPower(PTrigger.when(PCond.onTurnEnd(), PMod.perPower(CooledPower.DATA).setTarget(PCLCardTarget.UseParent), PMove.loseHp(PCLCardTarget.AllEnemy, 1)));
    }
}

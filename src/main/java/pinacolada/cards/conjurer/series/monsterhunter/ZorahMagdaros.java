package pinacolada.cards.conjurer.series.monsterhunter;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.powers.conjurer.PetraPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMod;
import pinacolada.skills.PMove;
import pinacolada.skills.conjurer.conditions.PCond_PayLevel;
import pinacolada.skills.delay.DelayTiming;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class ZorahMagdaros extends PCLCard {
    public static final PCLCardData DATA = register(ZorahMagdaros.class, ConjurerResources.conjurer)
            .setSummon(4, CardRarity.RARE, PCLAttackType.Normal, PCLCardTarget.AllEnemy, DelayTiming.EndOfTurnFirst)
            .setDamage(11, 1)
            .setHp(40, 1)
            .setAffinities(PCLAffinity.Orange.make(2), PCLAffinity.Red.make(1))
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public ZorahMagdaros() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.EARTH);
        addGainPower(PTrigger.interactable(new PCond_PayLevel(1, PCLAffinity.Orange), CMod.bonusPerLevel(3, PCLAffinity.Orange), PMove.applyToEnemies(7, PetraPower.DATA, IgnisPower.DATA)));
    }
}

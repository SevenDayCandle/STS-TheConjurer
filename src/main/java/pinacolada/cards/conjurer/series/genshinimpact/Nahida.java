package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PBranchCond;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Nahida extends PCLCard {
    public static final PCLCardData DATA = register(Nahida.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.SPECIAL, PCLAttackType.Immaterial)
            .setDamage(2, 1)
            .setHp(4, 1)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Nahida() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.WIND);
        addGainPower(PTrigger.when(PBranchCond.branch
                (CCond.checkLevel(1, PCLAffinity.Blue, PCLAffinity.Green, PCLAffinity.Orange),
                        PMove.gainPlayer(2, PCLPowerData.Energized),
                        PMove.gainPlayer(3, PCLPowerData.NextTurnDraw),
                        PMove.gainPlayer(9, PCLPowerData.NextTurnBlock)
                )));
    }
}

package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;
import pinacolada.skills.delay.DelayTiming;
import pinacolada.skills.skills.PMultiSkill;

@VisibleCard
public class SuikaIbuki extends PCLCard {
    public static final PCLCardData DATA = register(SuikaIbuki.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.UNCOMMON, PCLAttackType.Normal, PCLCardTarget.AllEnemy, DelayTiming.EndOfTurnFirst)
            .setDamage(5, 2)
            .setHp(7, 1)
            .setAffinities(1, PCLAffinity.Red, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public SuikaIbuki() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.BLUNT_HEAVY);
        addUseMove(CCond.react(), PMultiSkill.join(PMove.retain(1), PMove.gainPlayer(1, PCLPowerHelper.DrawMinus)));
    }
}

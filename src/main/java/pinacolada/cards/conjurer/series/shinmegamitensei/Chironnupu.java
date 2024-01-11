package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.monsters.PCLIntentType;
import pinacolada.powers.conjurer.FlowPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Chironnupu extends PCLCard {
    public static final PCLCardData DATA = register(Chironnupu.class, ConjurerResources.conjurer)
            .setSummon(0, CardRarity.COMMON, PCLAttackType.Ranged)
            .setDamage(1, array(0, 0), 2, array(1, 0))
            .setHp(3, 1)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Chironnupu() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.BLUNT_LIGHT);
        addUseMove(PCond.intent(PCLCardTarget.Single, PCLIntentType.Buff, PCLIntentType.Debuff),
                PMove.gainPlayer(2, FlowPower.DATA).setUpgrade(0, 1));
    }
}

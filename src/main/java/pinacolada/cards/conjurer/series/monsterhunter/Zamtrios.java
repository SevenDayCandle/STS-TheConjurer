package pinacolada.cards.conjurer.series.monsterhunter;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.delay.DelayTiming;

@VisibleCard
public class Zamtrios extends PCLCard {
    public static final PCLCardData DATA = register(Zamtrios.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Normal, PCLCardTarget.Single, DelayTiming.EndOfTurnFirst)
            .setDamage(2, 1, 2)
            .setHp(5, 0)
            .setAffinities(2, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public Zamtrios() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.BITE).setBonus(PCond.checkPower(PCLCardTarget.Single, 2, PCLElementHelper.Blasted), 2, 1);
    }
}
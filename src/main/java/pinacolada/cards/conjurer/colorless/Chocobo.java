package pinacolada.cards.conjurer.colorless;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Chocobo extends PCLCard {
    public static final PCLCardData DATA = register(Chocobo.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Normal)
            .setDamage(2, 1)
            .setHp(3, 2)
            .setAffinities(PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.finalFantasy, true);

    public Chocobo() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.DAGGER);
        addUseMove(PCond.onWithdraw(), PMove.applyToTeam(1, PCLPowerHelper.Critical));
    }
}
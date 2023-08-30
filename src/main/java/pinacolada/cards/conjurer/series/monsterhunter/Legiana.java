package pinacolada.cards.conjurer.series.monsterhunter;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Legiana extends PCLCard {
    public static final PCLCardData DATA = register(Legiana.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.COMMON, PCLAttackType.Piercing)
            .setDamage(3, 0, 1)
            .setHp(6, 2)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public Legiana() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.CLAW);
        addUseMove(PCond.onSummon(), PMove.applyToEnemies(3, PCLElementHelper.Aqua).setUpgrade(1));
    }
}

package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

@VisibleCard
public class Seiryu extends PCLCard {
    public static final PCLCardData DATA = register(Seiryu.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.RARE, PCLAttackType.Piercing)
            .setDamage(4, array(1, 0))
            .setHp(10, array(2, 0))
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Seiryu() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.CLAW);
        addUseMove(PCond.cooldown(0), PMultiSkill.choose(
                CMove.gainMatter(10).setUpgrade(0, 3),
                PMove.applyToEnemies(2, PCLElementHelper.Ventus).setUpgrade(1)
        ));
    }
}

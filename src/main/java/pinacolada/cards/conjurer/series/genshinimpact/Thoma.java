package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiCond;

@VisibleCard
public class Thoma extends PCLCard {
    public static final PCLCardData DATA = register(Thoma.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.SPECIAL)
            .setDamage(2, 1)
            .setHp(6, 2)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Thoma() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SLASH_VERTICAL);
        addUseMove(PMultiCond.or(PCond.onSummon(), PCond.onWithdraw()), PMove.applyToEnemies(2, BlastedPower.DATA, PCLPowerData.Weak));
    }
}

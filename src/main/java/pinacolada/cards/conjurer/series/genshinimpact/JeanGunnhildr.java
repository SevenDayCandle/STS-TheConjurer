package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class JeanGunnhildr extends PCLCard {
    public static final PCLCardData DATA = register(JeanGunnhildr.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE)
            .setDamage(3, 2)
            .setHp(7, 1)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public JeanGunnhildr() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SLASH_HORIZONTAL);
        addUseMove(PCond.cooldown(1), PMove.applyToTeam(-1, PCLPowerData.Vulnerable));
    }
}

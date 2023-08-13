package pinacolada.cards.conjurer.series.honkai;


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
public class YaeSakura extends PCLCard {
    public static final PCLCardData DATA = register(YaeSakura.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Immaterial)
            .setDamage(3, 1)
            .setHp(4, 2)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.honkai, true);

    public YaeSakura() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.GHOST);
        addUseMove(PCond.onDeath(), PMove.applyToEveryone(2, PCLPowerHelper.Silenced));
    }
}

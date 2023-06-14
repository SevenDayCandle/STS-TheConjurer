package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;

@VisibleCard
public class LisaMinci extends PCLCard {
    public static final PCLCardData DATA = register(LisaMinci.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.COMMON, PCLAttackType.Immaterial)
            .setDamage(3, 0)
            .setHp(8, 3)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public LisaMinci() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.LIGHTNING);
        addUseMove(PCond.cooldown(1), PMod.scryPer(2), CMove.gainMatter(6).setUpgrade(1));
    }
}

package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.powers.conjurer.CooledPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMod;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Chongyun extends PCLCard {
    public static final PCLCardData DATA = register(Chongyun.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.COMMON)
            .setDamage(3, 1)
            .setHp(7, 2)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Chongyun() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.ICE);
        addUseMove(PCond.cooldown(1), PMove.applyToSingle(7, CooledPower.DATA));
    }
}

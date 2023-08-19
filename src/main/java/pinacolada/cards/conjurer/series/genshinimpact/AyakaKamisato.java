package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiCond;

@VisibleCard
public class AyakaKamisato extends PCLCard {
    public static final PCLCardData DATA = register(AyakaKamisato.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Brutal)
            .setRTags(PCLCardTag.Ethereal)
            .setDamage(8, 0, 2)
            .setHp(4, 1)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public AyakaKamisato() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.ICE);
        addUseMove(PMultiCond.or(PCond.onSummon(), PCond.onWithdraw()), PMove.loseHp(PCLCardTarget.All, 4));
    }
}

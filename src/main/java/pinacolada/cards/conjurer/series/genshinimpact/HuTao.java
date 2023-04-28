package pinacolada.cards.conjurer.series.genshinimpact;


import com.badlogic.gdx.graphics.Color;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class HuTao extends PCLCard {
    public static final PCLCardData DATA = register(HuTao.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Piercing, PCLCardTarget.RandomEnemy)
            .setRTags(PCLCardTag.Ethereal)
            .setDamage(2, 1, 2)
            .setHp(5, 0)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public HuTao() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLEnum.AttackEffect.BURN).setVFXColor(Color.VIOLET, Color.VIOLET);
        addUseMove(PCond.cooldown(0), PCond.exhaustRandom(1, PCLCardGroupHelper.DiscardPile), PMove.gainEnergy(1));
    }
}

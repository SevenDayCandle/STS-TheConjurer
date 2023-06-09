package pinacolada.cards.conjurer.series.monsterhunter;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.DelayTiming;

@VisibleCard
public class KuluYaKu extends PCLCard {
    public static final PCLCardData DATA = register(KuluYaKu.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.COMMON, PCLAttackType.Normal, PCLCardTarget.RandomEnemy, DelayTiming.EndOfTurnFirst)
            .setCostUpgrades(-1)
            .setDamage(4, 0, 0)
            .setHp(4, 0)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public KuluYaKu() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.CLAW);
        addUseMove(PCond.onDeath(), PMove.play(1, PCLCardTarget.RandomAlly, PCLCardGroupHelper.DiscardPile).edit(f -> f.setType(PCLEnum.CardType.SUMMON).setRandom()));
    }
}

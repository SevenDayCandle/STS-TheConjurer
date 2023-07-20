package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.EffekseerEFK;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class DarkEdge extends PCLCard {
    public static final PCLCardData DATA = register(DarkEdge.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.UNCOMMON, PCLAttackType.Brutal)
            .setDamage(10, 3)
            .setAffinities(PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.darkSouls, true);

    public DarkEdge() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.SWORD11);
        addUseMove(PCond.isAttacking(PCLCardTarget.Single), PMove.apply(PCLCardTarget.SelfSingle, 2, PCLPowerHelper.Blinded));
    }
}

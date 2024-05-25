package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.EffekseerEFK;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.conjurer.conditions.PCond_PayLevel;
import pinacolada.skills.skills.PBranchCond;

@VisibleCard
public class SeethingChaos extends PCLCard {
    public static final PCLCardData DATA = register(SeethingChaos.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.RARE, PCLAttackType.Ranged, PCLCardTarget.AllEnemy)
            .setDamage(11, 5)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public SeethingChaos() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.SWORD04);
        addUseMove(PBranchCond.branch(new PCond_PayLevel(5, PCLAffinity.Red), PMove.modifyDamage(6), PMove.selfExhaust()));
    }
}

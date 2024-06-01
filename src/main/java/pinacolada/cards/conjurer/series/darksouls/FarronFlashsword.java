package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.EffekseerEFK;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PBranchCond;
import pinacolada.skills.skills.base.conditions.PCond_HaveExhausted;
import pinacolada.skills.skills.base.conditions.PCond_HaveLastPlayed;

@VisibleCard
public class FarronFlashsword extends PCLCard {
    public static final PCLCardData DATA = register(FarronFlashsword.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.UNCOMMON, PCLAttackType.Normal)
            .setDamage(8, 3)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public FarronFlashsword() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.SWORD15);
        addUseMove(PBranchCond.branch(PCond.haveExhausted(), PMove.draw(3), PMove.exhaustRandom(1, PCLCardGroupHelper.DiscardPile)));
    }
}

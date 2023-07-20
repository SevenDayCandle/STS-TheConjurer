package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.EffekseerEFK;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class GreatCombustion extends PCLCard {
    public static final PCLCardData DATA = register(GreatCombustion.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.UNCOMMON, PCLAttackType.Piercing)
            .setDamage(4, 3)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public GreatCombustion() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.FIRE12);
        addUseMove(CCond.react(), PMod.perPowerSingle(PCLElementHelper.Blasted), PMove.dealDamageToAll(1));
    }
}

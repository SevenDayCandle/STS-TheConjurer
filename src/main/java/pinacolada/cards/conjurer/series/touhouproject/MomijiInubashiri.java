package pinacolada.cards.conjurer.series.touhouproject;


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
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class MomijiInubashiri extends PCLCard {
    public static final PCLCardData DATA = register(MomijiInubashiri.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Piercing)
            .setDamage(3, 0)
            .setHp(5, 2)
            .setAffinities(PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public MomijiInubashiri() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SLASH_HORIZONTAL);
        addUseMove(PCond.cooldown(2), PMod.scryPer(3), PMove.gainPlayer(2, PCLPowerHelper.Warding));
    }
}

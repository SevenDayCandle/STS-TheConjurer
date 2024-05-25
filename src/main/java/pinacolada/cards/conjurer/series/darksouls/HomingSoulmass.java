package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.EffekseerEFK;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.conjurer.conditions.PCond_PayLevel;
import pinacolada.skills.skills.base.moves.PMove_Fetch;

@VisibleCard
public class HomingSoulmass extends PCLCard {
    public static final PCLCardData DATA = register(HomingSoulmass.class, ConjurerResources.conjurer)
            .setAttack(0, CardRarity.UNCOMMON, PCLAttackType.Immaterial)
            .setDamage(6, 3)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public HomingSoulmass() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.SWORD04);
        addUseMove(new PCond_PayLevel(4, PCLAffinity.Blue), new PMove_Fetch());
    }
}

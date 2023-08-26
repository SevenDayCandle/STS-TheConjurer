package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.fields.PField_CardGeneric;

@VisibleCard
public class Nazrin extends PCLCard {
    public static final PCLCardData DATA = register(Nazrin.class, ConjurerResources.conjurer)
            .setSummon(0, CardRarity.COMMON, PCLAttackType.Normal)
            .setDamage(2, 1)
            .setHp(3, 2)
            .setAffinities(PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public Nazrin() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.BLUNT_LIGHT);
        addUseMove(PCond.cooldown(2), PMove.retain(2).edit(PField_CardGeneric::setRandom));
    }
}

package pinacolada.cards.conjurer.colorless;


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
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class TheDefect extends PCLCard {
    public static final PCLCardData DATA = register(TheDefect.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Immaterial)
            .setDamage(4, 2)
            .setHp(6, 1)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.slayTheSpire, true);

    public TheDefect() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.LIGHTNING);
        addGainPower(PTrigger.interactable(PCond.payEnergy(2), PMove.createRandom(1, 3).edit(f -> f.setColor(CardColor.BLUE)), PMove.modifyCostExactForTurn(0).useParent(true)));
    }
}

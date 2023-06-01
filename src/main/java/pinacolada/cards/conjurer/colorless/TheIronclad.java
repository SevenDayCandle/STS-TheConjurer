package pinacolada.cards.conjurer.colorless;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class TheIronclad extends PCLCard {
    public static final PCLCardData DATA = register(TheIronclad.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON)
            .setDamage(3, 1)
            .setHp(8, 2)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.slayTheSpire, true);

    public TheIronclad() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SLASH_HEAVY);
        addGainPower(PTrigger.interactable(PCond.payEnergy(2), PMove.createRandom(1, 3).edit(f -> f.setColor(CardColor.RED)), PMove.modifyCostExactForTurn(0).useParent(true)));
    }
}

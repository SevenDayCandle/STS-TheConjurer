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
public class TheSilent extends PCLCard {
    public static final PCLCardData DATA = register(TheSilent.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Ranged)
            .setDamage(2, 1, 2)
            .setHp(5, 2)
            .setAffinities(PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.slayTheSpire, true);

    public TheSilent() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.DAGGER);
        addGainPower(PTrigger.interactable(PCond.payEnergy(2), PMove.createRandom(1, 3).edit(f -> f.setColor(CardColor.GREEN)), PMove.modifyCostExactForTurn(0).useParent(true)));
    }
}

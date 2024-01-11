package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardSelection;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Odin extends PCLCard {
    public static final PCLCardData DATA = register(Odin.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.RARE, PCLAttackType.Piercing)
            .setDamage(5, 1)
            .setHp(11, 2)
            .setAffinities(1, PCLAffinity.Red, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Odin() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SLASH_HEAVY);
        addGainPower(PTrigger.interactable(
                CCond.payLevel(4, PCLAffinity.Red),
                PMove.fetch(1, PCLCardGroupHelper.DiscardPile).edit(f -> f.setAffinity(PCLAffinity.Red, PCLAffinity.Green).setOrigin(PCLCardSelection.Random)))
        );
    }
}

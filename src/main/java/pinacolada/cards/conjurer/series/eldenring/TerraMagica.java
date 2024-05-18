package pinacolada.cards.conjurer.series.eldenring;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PTrait;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class TerraMagica extends PCLCard {
    public static final PCLCardData DATA = register(TerraMagica.class, ConjurerResources.conjurer)
            .setPower(2, CardRarity.RARE)
            .setAffinities(PCLAffinity.Blue.make(2), PCLAffinity.Orange.make(1))
            .setLoadout(ConjurerPlayerData.eldenRing);

    public TerraMagica() {
        super(DATA);
    }

    public void setup(Object input) {
        addGainPower(PTrigger.passive(PCond.ifProperty(PCLAffinity.Blue), PMultiSkill.join(PTrait.damageMultiplier(35).setUpgrade(15), PTrait.blockMultiplier(35).setUpgrade(15))));
    }
}

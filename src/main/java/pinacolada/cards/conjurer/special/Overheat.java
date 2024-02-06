package pinacolada.cards.conjurer.special;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

@VisibleCard
public class Overheat extends PCLCard {
    public static final PCLCardData DATA = register(Overheat.class, ConjurerResources.conjurer)
            .setStatus(-2, CardRarity.SPECIAL, PCLCardTarget.All)
            .setTags(PCLCardTag.Unplayable, PCLCardTag.Ethereal)
            .setAffinities(PCLAffinity.Red);

    public Overheat() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PCond.onDraw(), PMultiSkill.join(PMove.draw(1), PMove.gain(2, BlastedPower.DATA).setUpgrade(1)));
    }
}
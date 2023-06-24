package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.special.primary.PRoot;

@VisibleRelic
public class SummonFile extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(SummonFile.class, ConjurerResources.conjurer)
            .setProps(RelicTier.RARE, LandingSound.MAGICAL);

    public SummonFile() {
        super(DATA);
    }

    public void setup() {
        addUseMove(new PRoot(), PMove.playCopy(1, PCLCardTarget.RandomAlly).edit(f -> f.setType(PCLEnum.CardType.SUMMON)));
    }
}
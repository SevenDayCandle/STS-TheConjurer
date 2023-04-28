package pinacolada.relics.conjurer;

import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.conjurer.series.genshinimpact.Klee_JumpyDumpty;
import pinacolada.relics.PCLRelic;
import pinacolada.resources.conjurer.ConjurerResources;

@VisibleRelic
public class StraightShooter extends PCLRelic {
    public static final String ID = createFullID(ConjurerResources.conjurer, StraightShooter.class);

    public StraightShooter() {
        super(ID, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public void onShuffle() {
        super.onShuffle();
        PCLActions.bottom.makeCardInDrawPile(new Klee_JumpyDumpty())
                .addCallback(card ->
                {
                    if (card instanceof PCLCard) {
                        card.upgrade();
                        card.applyPowers();
                    }

                });
        flash();
    }
}
package pinacolada.cards.conjurer.basic;

import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.skills.skills.PTrigger;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class Ecosystem extends PCLCard {
    public static final PCLCardData DATA = register(Ecosystem.class, ConjurerResources.conjurer)
            .setPower(2, CardRarity.RARE)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green, PCLAffinity.Orange)
            .setMaxCopies(2)
            .setCore();

    public Ecosystem() {
        super(DATA);
    }

    public void setup(Object input) {
        addGainPower(PTrigger.when(1, PCond.checkLevel(1),
                getSpecialMove(0, this::specialMove, 1)
        ).setUpgrade(1));
    }

    public void specialMove(PSpecialSkill move, PCLUseInfo info, PCLActions order) {
        PCLAffinity aff = info.getData(PCLAffinity.class);
        if (aff != null) {
            PCLElementHelper debuff = PCLElementHelper.get(aff);
            if (debuff != null) {
                for (AbstractCreature target : GameUtilities.getAllCharacters(true)) {
                    PCLActions.delayed.stabilizePower(info.source, target, debuff, move.amount);
                }
            }
        }
    }
}

package pinacolada.cards.conjurer.colorless;


import com.megacrit.cardcrawl.powers.StrengthPower;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class Enervate extends PCLCard {
    public static final PCLCardData DATA = register(Enervate.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Blue)
            .setCore(true);

    public Enervate() {
        super(DATA);
    }

    public void action(PSpecialSkill move, PCLUseInfo info, PCLActions order) {
        int strength = GameUtilities.getPowerAmount(info.target, StrengthPower.POWER_ID) / 2;
        order.applyPower(info.source, info.target, PCLCardTarget.Single, PCLPowerHelper.Strength, -(strength > 0 ? strength + move.amount : move.amount));
    }

    public void setup(Object input) {
        addSpecialMove(0, this::action, 2).setUpgrade(1);
    }
}

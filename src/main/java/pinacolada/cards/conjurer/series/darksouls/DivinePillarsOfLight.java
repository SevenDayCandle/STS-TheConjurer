package pinacolada.cards.conjurer.series.darksouls;


import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.StrengthPower;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class DivinePillarsOfLight extends PCLCard {
    public static final PCLCardData DATA = register(DivinePillarsOfLight.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.RARE, PCLCardTarget.AllEnemy)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public DivinePillarsOfLight() {
        super(DATA);
    }

    public void action(PSpecialSkill move, PCLUseInfo info, PCLActions order) {
        for (AbstractCreature target : move.getTargetListAsNew(info)) {
            int strength = GameUtilities.getPowerAmount(target, StrengthPower.POWER_ID) / 2;
            order.applyPower(info.source, target, PCLPowerData.Strength, -(strength > 0 ? strength + move.refreshAmount(info) : move.refreshAmount(info)));
        }
    }

    public void setup(Object input) {
        addSpecialMove(0, this::action, 2).setUpgrade(1);
    }
}

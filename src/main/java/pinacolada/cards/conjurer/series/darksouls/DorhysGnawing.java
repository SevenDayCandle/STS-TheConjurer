package pinacolada.cards.conjurer.series.darksouls;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import extendedui.EUIUtils;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.GameUtilities;
import pinacolada.utilities.RandomizedList;

import java.util.ArrayList;

@VisibleCard
public class DorhysGnawing extends PCLCard {
    public static final PCLCardData DATA = register(DorhysGnawing.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.UNCOMMON, PCLAttackType.Normal, PCLCardTarget.AllEnemy)
            .setDamage(11, 3)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public DorhysGnawing() {
        super(DATA);
    }

    public void action(PSpecialSkill move, PCLUseInfo info, PCLActions order) {
        RandomizedList<AbstractCard> choices = new RandomizedList<>();
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (GameUtilities.getPCLCardAffinityLevel(c, PCLAffinity.General, true) == 0) {
                choices.add(c);
            }
        }
        for (int i = 0; i < Math.min(move.amount, choices.size()); i++) {
            AbstractCard c = choices.retrieve(AbstractDungeon.cardRandomRng);
            if (c != null) {
                order.modifyAffinityLevel(c, new ArrayList<>(), 0, false, true);
            }
        }
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.BITE);
        addSpecialMove(0, this::action, 1);
    }
}
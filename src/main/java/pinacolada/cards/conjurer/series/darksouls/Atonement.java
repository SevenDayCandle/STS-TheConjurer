package pinacolada.cards.conjurer.series.darksouls;


import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.GameUtilities;

import java.util.ArrayList;

@VisibleCard
public class Atonement extends PCLCard {
    public static final PCLCardData DATA = register(Atonement.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Yellow, PCLAffinity.Silver)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public Atonement() {
        super(DATA);
    }

    public void action(PSpecialSkill move, PCLUseInfo info, PCLActions order) {
        for (AbstractCreature target : move.getTargetListAsNew(info)) {
            ArrayList<AbstractPower> playerPowers = new ArrayList<>();
            ArrayList<AbstractPower> enemyPowers = new ArrayList<>();
            for (AbstractPower po : info.source.powers) {
                if (GameUtilities.isPCLDebuff(po)) {
                    playerPowers.add(po);
                    PCLActions.top.removePower(info.source, po);
                }
            }
            for (AbstractPower po : target.powers) {
                if (GameUtilities.isPCLDebuff(po)) {
                    enemyPowers.add(po);
                    PCLActions.top.removePower(target, po);
                }
            }

            for (AbstractPower po : playerPowers) {
                po.owner = target;
                order.applyPower(info.source, target, po);
            }
            for (AbstractPower po : enemyPowers) {
                po.owner = info.source;
                order.applyPower(info.source, info.source, po);
            }

            int gained = enemyPowers.size() - playerPowers.size();
            int thp = gained * move.refreshAmount(info);
            if (thp > 0) {
                order.gainTemporaryHP(thp);
            }
        }
    }

    public void setup(Object input) {
        addSpecialMove(0, this::action, 5).setUpgrade(2);
    }
}

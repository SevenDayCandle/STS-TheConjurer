package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardAffinities;
import pinacolada.dungeon.CombatManager;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.powers.conjurer.ElementPowerData;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;
import pinacolada.utilities.GameUtilities;
import pinacolada.utilities.RandomizedList;

@VisibleRelic
public class PeriodicTable extends PCLRelic {
    public static final PCLRelicData DATA = register(PeriodicTable.class, ConjurerResources.conjurer)
            .setProps(RelicTier.STARTER, LandingSound.SOLID);

    public PeriodicTable() {
        super(DATA);
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();
        setCounter(getValue());
    }

    @Override
    public int getValue() {
        return 1;
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        super.onPlayCard(card, m);
        if (card != null && counter > 0) {
            PCLCardAffinities affs = GameUtilities.getPCLCardAffinities(card);
            if (affs != null) {
                RandomizedList<AbstractCreature> creatures = new RandomizedList<>();
                PCLUseInfo info = CombatManager.getLastInfo();
                if (info != null) {
                    for (AbstractCreature c : info.targets) {
                        if (GameUtilities.isEnemy(c)) {
                            creatures.add(c);
                        }
                    }
                }
                if (creatures.isEmpty()) {
                    if (m != null) {
                        creatures.add(m);
                    }
                    else {
                        creatures.add(GameUtilities.getRandomEnemy(true));
                    }
                }

                for (PCLAffinity av : PCLAffinity.getAvailableAffinities()) {
                    int lvl = affs.getLevel(av);
                    if (lvl > 0) {
                        for (AbstractCreature creature : creatures) {
                            PCLActions.last.applyPower(AbstractDungeon.player, creature, ElementPowerData.get(av), lvl);
                        }
                    }
                }
            }
            counter -= 1;
        }
    }
}
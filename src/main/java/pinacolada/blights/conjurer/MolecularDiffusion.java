package pinacolada.blights.conjurer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import extendedui.EUIUtils;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleBlight;
import pinacolada.blights.PCLBlight;
import pinacolada.blights.PCLBlightData;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardAffinities;
import pinacolada.cards.base.fields.PCLCardAffinity;
import pinacolada.dungeon.CombatManager;
import pinacolada.interfaces.subscribers.OnCardUsingSubscriber;
import pinacolada.powers.conjurer.ElementPowerData;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.GameUtilities;
import pinacolada.utilities.RandomizedList;

@VisibleBlight
public class MolecularDiffusion extends PCLBlight implements OnCardUsingSubscriber {
    public static final PCLBlightData DATA = register(MolecularDiffusion.class, ConjurerResources.conjurer);

    public MolecularDiffusion() {
        super(DATA);
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();

        CombatManager.subscribe(this);
    }

    @Override
    public void onUse(AbstractCard card, AbstractPlayer p, AbstractCreature m) {
        if (card != null) {
            PCLCardAffinities affs = GameUtilities.getPCLCardAffinities(card);
            if (affs != null) {
                PCLAffinity[] available = PCLAffinity.getAvailableAffinities();
                RandomizedList<AbstractCreature> creatures = new RandomizedList<>();
                if (card instanceof PCLCard) {
                    if (card.type != PCLEnum.CardType.SUMMON) {
                        ((PCLCard) card).pclTarget.getTargets(p, m, creatures);
                        creatures.removeIf(c -> !GameUtilities.isEnemy(c));
                    }
                }
                else if (m != null) {
                    creatures.add(m);
                }

                if (creatures.isEmpty()) {
                    creatures.add(GameUtilities.getRandomEnemy(true));
                }

                for (PCLCardAffinity aff : affs.getCardAffinities(true)) {
                    if (EUIUtils.any(available, a -> a == aff.type)) {
                        for (AbstractCreature creature : creatures) {
                            PCLActions.delayed.applyPower(p, creature, ElementPowerData.get(aff.type), aff.level);
                        }
                    }
                }
            }
        }
    }
}
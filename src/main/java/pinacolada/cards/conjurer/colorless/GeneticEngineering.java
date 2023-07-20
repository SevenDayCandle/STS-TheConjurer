package pinacolada.cards.conjurer.colorless;


import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import extendedui.EUIUtils;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.interfaces.subscribers.OnCardCreatedSubscriber;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.skills.skills.base.moves.PMove_Gain;
import pinacolada.utilities.GameUtilities;

import java.util.ArrayList;

@VisibleCard
public class GeneticEngineering extends PCLCard {
    public static final PCLCardData DATA = register(GeneticEngineering.class, ConjurerResources.conjurer)
            .setPower(0, CardRarity.UNCOMMON)
            .setUTags(PCLCardTag.Haste, PCLCardTag.Retain)
            .setAffinities(PCLAffinity.Blue.make(2), PCLAffinity.Silver.make())
            .setCore(true);

    public GeneticEngineering() {
        super(DATA);
    }

    public void setup(Object input) {
        addSpecialPower(0, (s, i) -> new GeneticEngineeringPower(i.source, s), 2);
    }

    public static class GeneticEngineeringPower extends PSpecialCardPower implements OnCardCreatedSubscriber {
        public GeneticEngineeringPower(AbstractCreature owner, PSkill move) {
            super(DATA, owner, move);
        }

        protected void applyToCard(AbstractCard c) {
            if (c.type == CardType.STATUS) {
                PCLCard pc = EUIUtils.safeCast(c, PCLCard.class);
                if (pc != null) {
                    PCLCardTag.Unplayable.set(pc, 0);
                    PCLCardTag.Exhaust.set(pc, 1);
                    pc.setTarget(PCLCardTarget.RandomEnemy);

                    ArrayList<PSkill<?>> newSkills = new ArrayList<>();
                    for (PSkill<?> skill : pc.getEffects()) {
                        PSkill<?> bottom = skill.getLowestChild();
                        if (bottom != null && bottom.isDetrimental()) {
                            // PMove_Gain effects such as Void are negative and should be inverted
                            if (bottom instanceof PMove_Gain && bottom.amount < 0) {
                                bottom.setAmount(-bottom.amount);
                            }
                            else {
                                bottom.setAmount(bottom.amount * move.amount);
                                if (bottom.target.targetsSelf()) {
                                    bottom.setTarget(PCLCardTarget.RandomEnemy);
                                }
                                else {
                                    bottom.setTarget(PCLCardTarget.Self);
                                }
                            }

                        }
                        newSkills.add(bottom);
                    }

                    if (pc.cost < 0) {
                        GameUtilities.modifyCostForCombat(pc, 0, false);
                    }

                    if (newSkills.size() == 0) {
                        newSkills.add(PMove.gainBlock(move.amount * (pc.cost + 1)));
                    }

                    pc.getEffects().clear();
                    for (PSkill<?> skill : newSkills) {
                        pc.addUseMove(skill);
                    }
                    pc.initializeDescription();
                }
                else {
                    PCLCardTag.Ethereal.set(c, 1);
                    PCLCardTag.Haste.set(c, 1);
                }
            }
        }

        @Override
        public void onCardCreated(AbstractCard c, boolean startOfBattle) {
            applyToCard(c);
        }

        @Override
        public void onInitialApplication() {
            super.onInitialApplication();

            for (AbstractCard c : GameUtilities.getCardsInAnyPile()) {
                applyToCard(c);
            }
        }
    }
}

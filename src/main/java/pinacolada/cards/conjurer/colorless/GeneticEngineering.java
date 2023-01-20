package pinacolada.cards.conjurer.colorless;


import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import extendedui.EUIUtils;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.interfaces.subscribers.OnCardCreatedSubscriber;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.utilities.GameUtilities;

import java.util.ArrayList;

@VisibleCard
public class GeneticEngineering extends PCLCard
{
    public static final PCLCardData DATA = register(GeneticEngineering.class, ConjurerResources.conjurer)
            .setPower(0, CardRarity.UNCOMMON)
            .setTags(PCLCardTag.Haste.make(0, 1))
            .setAffinities(PCLAffinity.Blue)
            .setColorless();

    public GeneticEngineering()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addSpecialPower(0, (s, i) -> new GeneticEngineeringPower(i.source, s), 1);
    }

    public static class GeneticEngineeringPower extends PSpecialCardPower implements OnCardCreatedSubscriber
    {

        public GeneticEngineeringPower(AbstractCreature owner, PSkill move)
        {
            super(DATA, owner, move);
        }

        @Override
        public void onCardCreated(AbstractCard c, boolean startOfBattle)
        {
            applyToCard(c);
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            for (AbstractCard c : GameUtilities.getCardsInAnyPile())
            {
                applyToCard(c);
            }
        }

        protected void applyToCard(AbstractCard c)
        {
            if (c.type == CardType.STATUS)
            {
                PCLCard pc = EUIUtils.safeCast(c, PCLCard.class);
                if (pc != null)
                {
                    PCLCardTag.Unplayable.set(pc, 0);
                    PCLCardTag.Exhaust.set(pc, 1);
                    pc.setTarget(PCLCardTarget.RandomEnemy);

                    ArrayList<PSkill<?>> newSkills = new ArrayList<>();
                    for (PSkill<?> skill : pc.getEffects())
                    {
                        PSkill<?> bottom = skill.getLowestChild();
                        if (bottom != null && bottom.isDetrimental())
                        {
                            bottom.setTarget(PCLCardTarget.RandomEnemy);
                        }
                        newSkills.add(bottom);
                    }

                    if (newSkills.size() == 0)
                    {
                        newSkills.add(PMove.gain(1, PCLPowerHelper.NextTurnBlock));
                    }

                    pc.getEffects().clear();
                    for (PSkill<?> skill : newSkills)
                    {
                        pc.addUseMove(skill);
                    }
                    pc.initializeDescription();
                }
                else
                {
                    PCLCardTag.Ethereal.set(c, 1);
                    PCLCardTag.Haste.set(c, 1);
                }
            }
        }
    }
}

package pinacolada.cards.conjurer.colorless;


import extendedui.EUIUtils;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.ChoiceCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.misc.CombatManager;
import pinacolada.misc.PCLUseInfo;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.skills.skills.PCustomCond;
import pinacolada.skills.skills.PTrigger;
import pinacolada.utilities.RandomizedList;
import pinacolada.utilities.WeightedList;

import java.util.ArrayList;

@VisibleCard
public class Geralt extends PCLCard
{
    public static final PCLCardData DATA = register(Geralt.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.RARE)
            .setDamage(4, 1)
            .setPriority(1)
            .setHp(11, 3)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue)
            .setCore(true);

    public Geralt()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.SLASH_HEAVY);
        addGainPower(PTrigger.interactable(-1, new GeraltCond(DATA, 1, 4)));
    }

    protected static class GeraltCond extends PCustomCond
    {
        public ArrayList<PSkill<?>> requests;

        public GeraltCond(PCLCardData data, int amount, int choices)
        {
            super(data, 0, amount, choices);
        }

        protected void useImpl(PCLUseInfo info)
        {
            requests = CombatManager.getCombatData(this.effectID, new ArrayList<>());
            if (requests.isEmpty())
            {
                populateRequests();
            }
            PCLActions.bottom.tryChooseSkill(cardData, amount, info.source, info.target, requests)
                    .addCallback(choiceCards ->
                    {
                        for (ChoiceCard<PSkill<?>> c : choiceCards)
                        {
                            requests.remove(c.value);
                        }
                    });
        }

        protected void populateRequests()
        {
            WeightedList<PSkill<?>> choices = new WeightedList<>();
            choices.add(PCond.payEnergy(2), 2);
            choices.add(PCond.exhaust(2, PCLCardGroupHelper.Hand).edit(f -> f.setAffinity(PCLAffinity.getRandomAvailableAffinity())), 2);
            choices.add(PCond.exhaust(4, PCLCardGroupHelper.DiscardPile).edit(f -> f.setAffinity(PCLAffinity.getRandomAvailableAffinity())), 2);
            choices.add(PCond.discard(2).edit(f -> f.setAffinity(PCLAffinity.getRandomAvailableAffinity())), 2);
            choices.add(PCond.payEnergy(1), 1);
            choices.add(PCond.exhaust(1, PCLCardGroupHelper.Hand).edit(f -> f.setAffinity(PCLAffinity.getRandomAvailableAffinity())), 1);
            choices.add(PCond.discard(1).edit(f -> f.setAffinity(PCLAffinity.getRandomAvailableAffinity())), 1);

            RandomizedList<PSkill<?>>[] rewards = EUIUtils.array(new RandomizedList<>(), new RandomizedList<>());
            rewards[1].add(PMove.draw(4));
            rewards[1].add(PMove.apply(PCLCardTarget.None, 3, PCLPowerHelper.Energized));
            rewards[1].add(PMove.apply(PCLCardTarget.None, 3, PCLPowerHelper.Strength));
            rewards[1].add(PMove.gainGold(15));
            rewards[1].add(PMove.modifyDamage(5));
            rewards[0].add(PMove.apply(PCLCardTarget.None, 2, PCLPowerHelper.Energized));
            rewards[0].add(PMove.apply(PCLCardTarget.None, 2, PCLPowerHelper.Thorns));
            rewards[0].add(PMove.modifyDamage(2));
            rewards[0].add(PMove.draw(2));

            while (requests.size() < extra && !choices.isEmpty())
            {
                WeightedList<PSkill<?>>.Item option = choices.retrieveWithWeight(rng, true);
                if (option != null)
                {
                    PSkill<?> request = option.object;
                    int index = option.weight;
                    if (index < rewards.length)
                    {
                        PSkill<?> reward = rewards[index].retrieve(rng, true);
                        request.setChild(reward);
                        requests.add(reward);
                    }
                }
            }
        }
    }
}

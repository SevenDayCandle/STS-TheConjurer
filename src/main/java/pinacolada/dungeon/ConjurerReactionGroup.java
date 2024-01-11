package pinacolada.dungeon;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.random.Random;
import extendedui.EUIUtils;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.interfaces.providers.ValueProvider;
import pinacolada.skills.PSkill;
import pinacolada.utilities.GameUtilities;
import pinacolada.utilities.WeightedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConjurerReactionGroup implements ValueProvider {
    private final WeightedList<PSkill<?>> upgrades;
    private int maxWeight;
    private int upgradeTimes;
    private List<PSkill<?>> skills;
    public final PCLAffinity a1;
    public final PCLAffinity a2;

    public ConjurerReactionGroup(PCLAffinity a1, PCLAffinity a2, List<PSkill<?>> skills) {
        this(a1, a2, skills, 0);
    }

    public ConjurerReactionGroup(PCLAffinity a1, PCLAffinity a2, List<PSkill<?>> skills, int upgradeTimes) {
        this.a1 = a1;
        this.a2 = a2;
        this.upgradeTimes = upgradeTimes;
        setSkills(skills);
        upgrades = new WeightedList<>();
    }

    public void addSkills(List<PSkill<?>> skills) {
        this.skills.addAll(skills);
        for (PSkill<?> skill : skills) {
            skill.setSource(this);
        }
    }

    public ConjurerReactionGroup addUpgrade(PSkill<?> skill, int weight) {
        upgrades.add(skill, weight);
        maxWeight = Math.max(weight, maxWeight);
        return this;
    }

    public ConjurerReactionGroup createCopyWithUpgrade(boolean addSkill) {
        ConjurerReactionGroup copy = new ConjurerReactionGroup(a1, a2, EUIUtils.map(skills, PSkill::makeCopy), upgradeTimes);
        copy.upgrades.addAll(upgrades);

        if (addSkill) {
            WeightedList<PSkill<?>>.Item upgrade = copy.getUpgrade(AbstractDungeon.cardRng);
            copy.skills.add(upgrade.object);
            if (upgrade.weight < copy.maxWeight / 2) {
                copy.skills.get(0).setAmount((int) (copy.skills.get(0).amount * 0.75));
            }
        }
        else {
            copy.setUpgradeLevel(upgradeTimes + 1);
        }

        return copy;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public List<PSkill<?>> getSkills() {
        return skills;
    }

    public String getSkillsText() {
        return PSkill.joinEffectTexts(skills, " ", PCLCardTarget.Self, null, true);
    }

    public WeightedList<PSkill<?>>.Item getUpgrade(Random rng) {
        return upgrades.retrieveWithWeight(rng, true);
    }

    public void refresh() {
    }

    public void setSkills(List<PSkill<?>> skills) {
        this.skills = skills;
        for (PSkill<?> skill : skills) {
            skill.setSource(this);
        }
    }

    public void setUpgradeLevel(int level) {
        upgradeTimes = level;
        for (PSkill<?> skill : skills) {
            skill.setAmountFromCard();
        }
    }

    @Override
    public int timesUpgraded() {
        return upgradeTimes;
    }
}

package pinacolada.cards.conjurer.colorless;


import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import extendedui.EUIUtils;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.characters.ConjurerCharacter;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLClickableUse;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.skills.conjurer.conditions.PCond_PayMatter;
import pinacolada.skills.fields.PField_Power;
import pinacolada.skills.skills.base.conditions.PCond_PayEnergy;
import pinacolada.skills.skills.base.primary.PLimit_Limited;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class Megaman extends PCLCard {
    public static final PCLCardData DATA = register(Megaman.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Ranged)
            .setDamage(2, array(1, 0), 2, array(0, 1))
            .setHp(5, 2)
            .setAffinities(PCLAffinity.Yellow, PCLAffinity.Silver)
            .setLoadout(ConjurerPlayerData.megaman, true);

    public Megaman() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.ELECTRIC);
        addSpecialPower(0, (s, i) -> new MegamanPower(i.source, i.source, s), 1, 1);
    }

    public static class MegamanPower extends PSpecialCardPower {
        public static final PCLPowerData PDATA = createFromCard(MegamanPower.class, DATA);
        protected static PCLClickableUse use;

        public MegamanPower(AbstractCreature owner, AbstractCreature source, PSkill<?> move) {
            super(PDATA, owner, source, move);

            AbstractMonster enemy = GameUtilities.getRandomEnemy(false);
            if (enemy != null) {
                PSkill<?> monsterSkill = ConjurerPlayerData.getSkillForMonster(enemy.id);
                int cost = ConjurerPlayerData.getCostForMonster(enemy.id);
                boolean metaScaling = monsterSkill.isMetascaling() ||
                        (monsterSkill.fields instanceof PField_Power && EUIUtils.any(((PField_Power) monsterSkill.fields).powers, PCLPowerData::isMetascaling));
                PSkill<?> costSkill = AbstractDungeon.player instanceof ConjurerCharacter ? new PCond_PayMatter(cost * 10) : new PCond_PayEnergy(cost);
                costSkill.setChild(monsterSkill);
                if (metaScaling) {
                    PLimit_Limited limit = new PLimit_Limited();
                    limit.setChild(costSkill);
                    costSkill = limit;
                }
                triggerCondition = new PCLClickableUse(this, costSkill, 1, !metaScaling, true);
            }
        }

        public String getUpdatedDescription() {
            return this.triggerCondition != null ? this.triggerCondition.move.getText() : super.getUpdatedDescription();
        }
    }
}

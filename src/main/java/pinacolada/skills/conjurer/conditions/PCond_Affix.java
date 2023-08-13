package pinacolada.skills.conjurer.conditions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import extendedui.EUIUtils;
import extendedui.ui.tooltips.EUIKeywordTooltip;
import extendedui.ui.tooltips.EUITooltip;
import pinacolada.annotations.VisibleSkill;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.CombatManager;
import pinacolada.interfaces.subscribers.*;
import pinacolada.monsters.PCLCardAlly;
import pinacolada.monsters.PCLCreature;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.resources.pcl.PCLCoreImages;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.fields.PField_CardCategory;
import pinacolada.skills.skills.PDelegateCardCond;
import pinacolada.skills.skills.base.conditions.PCond_OnAllyTrigger;
import pinacolada.utilities.GameUtilities;
import pinacolada.utilities.PCLRenderHelpers;

import static extendedui.EUIGameUtils.scale;

@VisibleSkill
public class PCond_Affix extends PDelegateCardCond implements OnAllyTriggerSubscriber, OnModifyBlockLastSubscriber, OnModifyBlockFirstSubscriber, OnModifyDamageGiveFirstSubscriber, OnModifyDamageGiveLastSubscriber {
    public static final PSkillData<PField_CardCategory> DATA = register(PCond_OnAllyTrigger.class, PField_CardCategory.class, -1, 1)
            .pclOnly()
            .selfTarget();

    public PCond_Affix() {
        super(DATA);
    }

    public PCond_Affix(PSkillSaveData content) {
        super(DATA, content);
    }

    public PCond_Affix(int amount, PCLAffinity... affinities) {
        super(DATA);
        setAmount(amount);
        fields.setAffinity(affinities);
    }

    @Override
    public String getSubText(PCLCardTarget perspective) {
        if (isWhenClause()) {
            return TEXT.cond_whenAObjectIs(fields.getFullSummonStringSingular(), getDelegatePastText());
        }

        EUITooltip tip;
        switch (amount) {
            case 1:
                tip = ConjurerResources.conjurer.tooltips.frontAffix;
                break;
            case -1:
                tip = ConjurerResources.conjurer.tooltips.backAffix;
                break;
            default:
                tip = getDelegateTooltip();
        }

        if (isBranch() || fields.isFilterEmpty()) {
            return tip.title;
        }
        return TEXT.subjects_withX(tip.title, fields.getFullSummonStringSingular());
    }

    @Override
    public EUIKeywordTooltip getDelegateTooltip() {
        return ConjurerResources.conjurer.tooltips.affix;
    }

    @Override
    public void onAllyTrigger(PCLCard card, PCLCardAlly ally, PCLCardAlly caller) {
        if (canAffixAlly(caller, ally)) {
            triggerOnCard(card, ally);
        }
    }

    protected boolean canAffixAlly(PCLCardAlly source, PCLCardAlly target) {
        if (target.card == null) {
            return false;
        }
        // Immediately in the front or back
        if (amount == 0) {
            return Math.abs(target.index - source.index) == 1 && fields.getFullCardFilter().invoke(target.card);
        }
        // Positive means in back, negative means in front
        return target.index - source.index == amount && fields.getFullCardFilter().invoke(target.card);
    }

    protected boolean canAffixCard(AbstractCard card) {
        PCLCardAlly other = EUIUtils.safeCast(GameUtilities.getCardOwner(card), PCLCardAlly.class);
        if (other != null) {
            PCLCardAlly self = EUIUtils.safeCast(GameUtilities.getCardOwner(sourceCard), PCLCardAlly.class);
            if (self != null) {
                return canAffixAlly(self, other);
            }
        }
        return false;
    }

    // Skip cond checks on self
    @Override
    public float onModifyBlockFirst(float v, AbstractCard card) {
        if (canAffixCard(card) && this.childEffect != null) {
            return this.childEffect.modifyBlockFirst(getInfo(getSourceCreature()), v);
        }
        return v;
    }

    @Override
    public float onModifyBlockLast(float v, AbstractCard card) {
        if (canAffixCard(card) && this.childEffect != null) {
            return this.childEffect.modifyBlockLast(getInfo(getSourceCreature()), v);
        }
        return v;
    }

    @Override
    public float onModifyDamageGiveFirst(float v, DamageInfo.DamageType damageType, AbstractCreature source, AbstractCreature target, AbstractCard card) {
        if (canAffixCard(card) && this.childEffect != null) {
            return this.childEffect.modifyDamageGiveFirst(getInfo(target), v);
        }
        return v;
    }

    @Override
    public float onModifyDamageGiveLast(float v, DamageInfo.DamageType damageType, AbstractCreature source, AbstractCreature target, AbstractCard card) {
        if (canAffixCard(card) && this.childEffect != null) {
            return this.childEffect.modifyDamageGiveLast(getInfo(target), v);
        }
        return v;
    }

    @Override
    public float renderIntentIcon(SpriteBatch sb, PCLCardAlly ally, float startY) {
        // Front
        if (amount <= 0) {
            renderIntentIconImpl(sb, ally, ally.index > 0 ? CombatManager.summons.summons.get(ally.index - 1) : null, startY, false);
        }
        // Back
        if (amount >= 0) {
            renderIntentIconImpl(sb, ally, ally.index < CombatManager.summons.summons.size() ? CombatManager.summons.summons.get(ally.index + 1) : null, startY, false);
        }

        return startY + PGR.core.tooltips.cooldown.icon.getRegionHeight() + Settings.scale * 10f;
    }

    protected void renderIntentIconImpl(SpriteBatch sb, PCLCardAlly ally, PCLCardAlly other, float startY, boolean flip) {
        if (other != null) {
            boolean canActivate = canAffixAlly(ally, other);
            Color iconColor = canActivate ? Color.WHITE : PCLCreature.TAKEN_TURN_COLOR;
            Texture icon = PCLCoreImages.Core.backArrow.texture();
            PCLRenderHelpers.drawGrayscaleIf(sb,
                    s -> {
                        PCLRenderHelpers.drawCentered(s, iconColor, icon, ally.intentHb.cX + (flip ? scale(-32) : scale(32)), startY, icon.getWidth(), icon.getHeight(), 1f, 0f, flip, false);
                        if (fields.affinities.size() == 1) {
                            Texture afIcon = fields.affinities.get(0).getIcon();
                            PCLRenderHelpers.drawCentered(s, iconColor, afIcon, ally.intentHb.cX + (flip ? scale(-32) : scale(32)), startY, afIcon.getWidth(), afIcon.getHeight(), 1f, 0f, flip, false);
                        }
                    },
                    !canActivate);
        }
    }
}

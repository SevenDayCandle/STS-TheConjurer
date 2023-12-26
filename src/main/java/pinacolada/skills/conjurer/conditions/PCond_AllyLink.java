package pinacolada.skills.conjurer.conditions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import extendedui.EUI;
import extendedui.EUIUtils;
import extendedui.ui.tooltips.EUIKeywordTooltip;
import extendedui.ui.tooltips.EUITooltip;
import extendedui.utilities.EUIColors;
import pinacolada.annotations.VisibleSkill;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.CombatManager;
import pinacolada.interfaces.subscribers.*;
import pinacolada.monsters.PCLCardAlly;
import pinacolada.monsters.PCLCreature;
import pinacolada.resources.conjurer.ConjurerImages;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.resources.pcl.PCLCoreStrings;
import pinacolada.skills.PCond;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.fields.PField;
import pinacolada.skills.fields.PField_CardCategory;
import pinacolada.skills.skills.PBranchCond;
import pinacolada.skills.skills.PDelegateCardCond;
import pinacolada.utilities.GameUtilities;
import pinacolada.utilities.PCLRenderHelpers;

import java.util.Collections;

@VisibleSkill
public class PCond_AllyLink extends PDelegateCardCond
        implements OnAllySummonSubscriber, OnAllyTriggerSubscriber, OnAllyWithdrawSubscriber,
                   OnModifyBlockLastSubscriber, OnModifyBlockFirstSubscriber, OnModifyDamageGiveFirstSubscriber, OnModifyDamageGiveLastSubscriber, OnModifyDamageReceiveFirstSubscriber, OnModifyDamageReceiveLastSubscriber  {
    protected static final float ARROW_SIZE = Settings.scale * 48;
    protected static final float ICON_SIZE = Settings.scale * 29;
    public static final PSkillData<PField_CardCategory> DATA = register(PCond_AllyLink.class, PField_CardCategory.class, -1, 1)
            .pclOnly()
            .noTarget();
    protected float flashTimerBack;
    protected float flashTimerFront;

    public PCond_AllyLink() {
        super(DATA);
    }

    public PCond_AllyLink(PSkillSaveData content) {
        super(DATA, content);
    }

    public PCond_AllyLink(int amount, PCLAffinity... affinities) {
        super(DATA);
        setAmount(amount);
        fields.setAffinity(affinities);
    }

    protected boolean canAffixCard(AbstractCard card) {
        PCLCardAlly other = EUIUtils.safeCast(GameUtilities.getCardOwner(card), PCLCardAlly.class);
        if (other != null && source instanceof AbstractCard) {
            PCLCardAlly self = EUIUtils.safeCast(GameUtilities.getCardOwner((AbstractCard) source), PCLCardAlly.class);
            if (self != null) {
                return canLinkAlly(self, other);
            }
        }
        return false;
    }

    protected boolean canLinkAlly(PCLCardAlly source, PCLCardAlly target) {
        if (target.card == null) {
            return false;
        }
        // Immediately in the front or back
        if (amount == 0) {
            return Math.abs(source.index - target.index) == 1 && fields.getFullCardFilter().invoke(target.card);
        }
        // Positive means in front, negative means in back
        return source.index - target.index == amount && fields.getFullCardFilter().invoke(target.card);
    }

    protected void flash(PCLCardAlly ally, boolean flip) {
        float x = ally.hb.cX + (flip ? -ARROW_SIZE * 2 : ARROW_SIZE * 2);
        float y = ally.hb.cY + ally.getBobEffect().y;
        Texture icon = ConjurerImages.Core.affix.texture();
        if (flip) {
            flashTimerBack = 1f;
        }
        else {
            flashTimerFront = 1f;
        }
    }

    @Override
    public EUIKeywordTooltip getDelegateTooltip() {
        return ConjurerResources.conjurer.tooltips.link;
    }

    @Override
    public String getSubText(PCLCardTarget perspective, Object requestor) {
        if (isWhenClause()) {
            return TEXT.cond_aObjectIs(fields.getFullSummonStringSingular(), getDelegatePastText());
        }

        EUITooltip tip;
        switch (amount) {
            case 1:
                tip = ConjurerResources.conjurer.tooltips.frontLink;
                break;
            case -1:
                tip = ConjurerResources.conjurer.tooltips.backLink;
                break;
            default:
                tip = getDelegateTooltip();
        }

        if (isBranch()) {
            return tip.getTitleHighlighted();
        }
        return TEXT.subjects_withX(tip.getTitleHighlighted(), fields.getCardXPrefixString(PField::getAffinityOrString, PCLCoreStrings::joinWithOr));
    }

    @Override
    public String getText(PCLCardTarget perspective, Object requestor, boolean addPeriod) {
        // Perspective is from a single ally
        return getCapitalSubText(perspective, requestor, addPeriod) + (childEffect != null ? ((childEffect instanceof PCond && !(childEffect instanceof PBranchCond) ? EFFECT_SEPARATOR : COLON_SEPARATOR) + childEffect.getText(PCLCardTarget.SingleAlly, requestor, addPeriod)) : "");
    }

    @Override
    public void onAllySummon(PCLCardAlly pclCardAlly, PCLCard pclCard, PCLCard pclCard1) {
        CombatManager.subscribe(OnModifyBlockFirstSubscriber.class, this);
        CombatManager.subscribe(OnModifyBlockLastSubscriber.class, this);
        CombatManager.subscribe(OnModifyDamageGiveFirstSubscriber.class, this);
        CombatManager.subscribe(OnModifyDamageGiveLastSubscriber.class, this);
        CombatManager.subscribe(OnModifyDamageReceiveFirstSubscriber.class, this);
        CombatManager.subscribe(OnModifyDamageReceiveLastSubscriber.class, this);
    }

    @Override
    public void onAllyTrigger(PCLCard card, AbstractCreature target, PCLCardAlly ally, PCLCardAlly caller) {
        if (canLinkAlly(caller, ally)) {
            useFromTrigger(generateInfo(ally, target).setData(Collections.singletonList(ally.card)));
            flash(caller, ally.index > caller.index);
        }
    }

    @Override
    public void onAllyWithdraw(PCLCard pclCard, PCLCardAlly pclCardAlly) {
        unsubscribeFromAll();
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
    public float onModifyDamageReceiveFirst(float v, DamageInfo.DamageType type, AbstractCreature source, AbstractCreature target, AbstractCard card) {
        if (target instanceof PCLCardAlly && canAffixCard(((PCLCardAlly) target).card) && this.childEffect != null) {
            return this.childEffect.modifyDamageReceiveFirst(getInfo(target), v, type);
        }
        return v;
    }

    @Override
    public float onModifyDamageReceiveLast(float v, DamageInfo.DamageType type, AbstractCreature source, AbstractCreature target, AbstractCard card) {
        if (target instanceof PCLCardAlly && canAffixCard(((PCLCardAlly) target).card) && this.childEffect != null) {
            return this.childEffect.modifyDamageReceiveLast(getInfo(target), v, type);
        }
        return v;
    }

    @Override
    public float renderIntentIcon(SpriteBatch sb, PCLCardAlly ally, float startY, boolean forPreview) {
        float delta = EUI.delta();
        // Front
        if (amount >= 0) {
            renderIntentIconImpl(sb, ally, ally.index > 0 ? CombatManager.summons.summons.get(ally.index - 1) : null, flashTimerFront, false);
            flashTimerFront -= delta;
        }
        // Back
        if (amount <= 0) {
            renderIntentIconImpl(sb, ally, ally.index < CombatManager.summons.summons.size() - 1 ? CombatManager.summons.summons.get(ally.index + 1) : null, flashTimerBack, true);
            flashTimerBack -= delta;
        }

        // Affix renders out of place
        return startY;
    }

    protected void renderIntentIconImpl(SpriteBatch sb, PCLCardAlly ally, PCLCardAlly other, float flash, boolean flip) {
        if (other != null) {
            boolean canActivate = canLinkAlly(ally, other);
            float x = ally.hb.cX + (flip ? -(ARROW_SIZE + ICON_SIZE) : (ARROW_SIZE + ICON_SIZE));
            float y = ally.hb.cY + ally.getBobEffect().y;
            Color iconColor = canActivate ? Color.WHITE : PCLCreature.TAKEN_TURN_COLOR;
            Texture icon = ConjurerImages.Core.affix.texture();
            PCLRenderHelpers.drawGrayscaleIf(sb,
                    s -> {
                        renderIntentIconProperty(sb, other, iconColor, x, y);
                        PCLRenderHelpers.drawCentered(s, iconColor, icon, x, y, ARROW_SIZE, ARROW_SIZE, 1f, 0f, flip, false);
                    },
                    !canActivate);
            if (flash > 0) {
                float baseScale = 1f - flash;
                Color flashColor = EUIColors.white(flash);
                sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
                PCLRenderHelpers.drawCentered(sb, flashColor, icon, x, y, ARROW_SIZE, ARROW_SIZE, 1f + baseScale / 1.3f, 0f, flip, false);
                PCLRenderHelpers.drawCentered(sb, flashColor, icon, x, y, ARROW_SIZE, ARROW_SIZE, 1f + baseScale * 1.1f, 0f, flip, false);
                PCLRenderHelpers.drawCentered(sb, flashColor, icon, x, y, ARROW_SIZE, ARROW_SIZE, 1f + baseScale * 2f, 0f, flip, false);
                sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            }
        }
    }

    protected void renderIntentIconProperty(SpriteBatch sb, PCLCardAlly other, Color iconColor, float x, float y) {
        if (fields.affinities.size() == 1) {
            PCLRenderHelpers.drawCentered(sb, iconColor, fields.affinities.get(0).getIcon(), x, y, ICON_SIZE, ICON_SIZE, 1f, 0f, false, false);
        }
        else if (fields.affinities.size() > 0 && other.card != null) {
            for (PCLAffinity affinity : fields.affinities) {
                if (GameUtilities.hasAffinity(other.card, affinity)) {
                    PCLRenderHelpers.drawCentered(sb, iconColor, affinity.getIcon(), x, y, ICON_SIZE, ICON_SIZE, 1f, 0f, false, false);
                    break;
                }
            }
        }
    }
}

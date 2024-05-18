package pinacolada.cards.conjurer.colorless;


import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import extendedui.EUIUtils;
import extendedui.ui.panelitems.CardPoolPanelItem;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cardmods.SkillModifier;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLDynamicCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.interfaces.markers.EditorCard;
import pinacolada.interfaces.subscribers.OnCardCreatedSubscriber;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.skills.conjurer.conditions.PCond_AllyLink;
import pinacolada.skills.skills.*;
import pinacolada.skills.skills.base.conditions.PCond_OnAllyDeath;
import pinacolada.skills.skills.base.conditions.PCond_OnAllyTrigger;
import pinacolada.skills.skills.base.conditions.PCond_OnSummon;
import pinacolada.skills.skills.base.conditions.PCond_OnWithdraw;
import pinacolada.skills.skills.base.moves.PMove_StackCustomPower;
import pinacolada.utilities.GameUtilities;
import pinacolada.utilities.RandomizedList;

@VisibleCard
public class GraffitiForm extends PCLCard {
    public static final PCLCardData DATA = register(GraffitiForm.class, ConjurerResources.conjurer)
            .setPower(0, CardRarity.SPECIAL)
            .setUTags(PCLCardTag.Innate)
            .setAffinities(PCLAffinity.Star)
            .setCore(true);

    public GraffitiForm() {
        super(DATA);
    }

    public void setup(Object input) {
        addSpecialPower(0, (t, o, s) -> new GraffitiFormPower(t, o, s), 1, 3);
    }

    public static class GraffitiFormPower extends PSpecialCardPower implements OnCardCreatedSubscriber {
        public static final PCLPowerData PDATA = createFromCard(GraffitiFormPower.class, DATA);
        protected SkillModifier override;

        public GraffitiFormPower(AbstractCreature owner, AbstractCreature source, PSkill<?> move) {
            super(PDATA, owner, source, move);
        }

        protected void chooseSkill() {
            final CardGroup choice = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            final int limit = Math.max(move.extra, move.amount);
            RandomizedList<PSkill<?>> skills = getSkills();
            while (choice.size() < limit) {
                choice.addToBottom(makeCard(skills));
            }
            PCLActions.bottom.selectFromPile(name, move.amount, choice)
                    .addCallback(cards -> {
                        for (AbstractCard c : cards) {
                            if (c instanceof EditorCard) {
                                PSkill<?> skill = ((EditorCard) c).getEffect(0);
                                if (skill == null) {
                                    skill = PMove.draw(1);
                                }
                                override = new SkillModifier(skill);
                            }
                        }
                    });
        }

        protected RandomizedList<PSkill<?>> getSkills() {
            RandomizedList<PSkill<?>> skills = new RandomizedList<>();
            if (AbstractDungeon.player != null) {
                for (AbstractCard c : CardPoolPanelItem.getAllCards().group) {
                    if (c instanceof EditorCard) {
                        skills.addAll(EUIUtils.filter(((EditorCard) c).getEffects(), e -> !(
                                e instanceof PCustomCond || e instanceof PSpecialSkill || e instanceof PSpecialPowerSkill || e instanceof PCustomMod
                                        || e instanceof PMove_StackCustomPower || e instanceof PCardPrimary || e instanceof PShift || e instanceof PCond_OnAllyDeath || e instanceof PCond_OnAllyTrigger
                                || e instanceof PCond_OnSummon || e instanceof PCond_OnWithdraw || e instanceof PCond_AllyLink || e.isMetascaling() || e.isBlank()
                        )));
                    }
                }
            }

            if (skills.size() < move.extra * 2) {
                skills.add(PMove.applyToRandom(2, PCLPowerData.Bruised));
                skills.add(PMove.applyToRandom(2, PCLPowerData.Weak));
                skills.add(PMove.applyToRandom(2, PCLPowerData.Vulnerable));
                skills.add(PMove.scry(3));
                skills.add(PMove.retain(2));
                skills.add(PMove.gain(2, PCLPowerData.NextTurnDraw));
                skills.add(PMove.gain(5, PCLPowerData.NextTurnBlock));
            }
            return skills;
        }

        protected AbstractCard makeCard(RandomizedList<PSkill<?>> skills) {
            PSkill<?> skill = skills.retrieve(AbstractDungeon.cardRandomRng, true);
            if (skill == null) {
                skill = PMove.draw(1);
            }
            PCLDynamicCardData data = new PCLDynamicCardData(DATA.ID, DATA.resources);
            data.addPSkill(skill);
            return data.create();
        }

        @Override
        public void onCardCreated(AbstractCard c, boolean b) {
            if (override == null) {
                chooseSkill();
            }
            if (override != null) {
                CardModifierManager.addModifier(c, override.makeCopy());
                if (PCLCardTag.Unplayable.has(c)) {
                    PCLCardTag.Unplayable.set(c, 0);
                    PCLCardTag.Exhaust.set(c, 1);
                    if (c instanceof PCLCard) {
                        ((PCLCard) c).setTarget(PCLCardTarget.RandomEnemy);
                    }
                }
                if (c.cost < 0) {
                    GameUtilities.modifyCostForCombat(c, 0, false);
                }
                flash();
            }
        }

        @Override
        public void onInitialApplication() {
            super.onInitialApplication();
            chooseSkill();
        }
    }
}

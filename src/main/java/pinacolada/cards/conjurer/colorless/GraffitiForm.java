package pinacolada.cards.conjurer.colorless;


import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import extendedui.EUIUtils;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cardmods.SkillModifier;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLDynamicCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.interfaces.markers.EditorCard;
import pinacolada.interfaces.subscribers.OnCardCreatedSubscriber;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.skills.skills.PCustomCond;
import pinacolada.skills.skills.base.moves.PMove_StackCustomPower;
import pinacolada.utilities.RandomizedList;

@VisibleCard
public class GraffitiForm extends PCLCard {
    public static final PCLCardData DATA = register(GraffitiForm.class, ConjurerResources.conjurer)
            .setPower(0, CardRarity.RARE)
            .setAffinities(PCLAffinity.Star)
            .setCore(true);

    public GraffitiForm() {
        super(DATA);
    }

    public void setup(Object input) {
        addSpecialPower(0, (s, i) -> new GraffitiFormPower(i.source, s), 1, 3).setUpgrade(1);
    }

    public static class GraffitiFormPower extends PSpecialCardPower implements OnCardCreatedSubscriber {
        protected SkillModifier override;

        public GraffitiFormPower(AbstractCreature owner, PSkill move) {
            super(DATA, owner, move);
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
            if (player != null) {
                for (AbstractCard c : player.hand.group) {
                    if (c instanceof EditorCard) {
                        skills.addAll(EUIUtils.filter(((EditorCard) c).getEffects(), e -> !(e instanceof PCustomCond || e instanceof PMove_StackCustomPower)));
                    }
                }
            }

            if (skills.size() < move.extra * 2) {
                skills.add(PMove.applyToRandom(2, PCLPowerHelper.Bruised));
                skills.add(PMove.applyToRandom(2, PCLPowerHelper.Weak));
                skills.add(PMove.applyToRandom(2, PCLPowerHelper.Vulnerable));
                skills.add(PMove.scry(2));
                skills.add(PMove.retain(1));
                skills.add(PMove.gain(2, PCLPowerHelper.NextTurnDraw));
                skills.add(PMove.gain(5, PCLPowerHelper.NextTurnBlock));
            }
            return skills;
        }

        protected AbstractCard makeCard(RandomizedList<PSkill<?>> skills) {
            PSkill<?> skill = skills.retrieve(rng, true);
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

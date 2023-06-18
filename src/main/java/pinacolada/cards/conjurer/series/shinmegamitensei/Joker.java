package pinacolada.cards.conjurer.series.shinmegamitensei;


import com.megacrit.cardcrawl.cards.AbstractCard;
import extendedui.EUIUtils;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardSelection;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.effects.PCLEffects;
import pinacolada.interfaces.markers.EditorCard;
import pinacolada.interfaces.providers.CooldownProvider;
import pinacolada.interfaces.providers.PointerProvider;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.skills.PSpecialSkill;

@VisibleCard
public class Joker extends PCLCard {
    public static final PCLCardData DATA = register(Joker.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Ranged)
            .setDamage(3, 0)
            .setHp(5, 2)
            .setAffinities(PCLAffinity.Star)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Joker() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.DARKNESS);
        addUseMove(PCond.cooldown(0), getSpecialMove(0, this::specialMove, 1).setUpgrade(1));
    }

    public void specialMove(PSpecialSkill move, PCLUseInfo info, PCLActions order) {
        order.selectFromPile(name, move.amount, player.discardPile)
                .setFilter(c -> c instanceof PointerProvider && EUIUtils.any(((PointerProvider) c).getFullEffects(), effect -> effect.hasChildType(CooldownProvider.class)))
                .setOrigin(PCLCardSelection.Random)
                .setAnyNumber(true)
                .addCallback(cards -> {
                    for (AbstractCard c : cards) {
                        PCLEffects.Queue.showCardBriefly(c);
                        EditorCard provider = EUIUtils.safeCast(c, EditorCard.class);
                        if (provider != null) {
                            provider.doEffects(ef -> ef.recurse(subEffect -> {
                                if (subEffect instanceof CooldownProvider) {
                                    subEffect.use(info, order);
                                }
                            }));
                        }
                    }
                });
    }
}

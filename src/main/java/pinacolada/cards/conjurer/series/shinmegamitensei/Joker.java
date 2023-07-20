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
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.effects.PCLEffects;
import pinacolada.interfaces.markers.SummonOnlyMove;
import pinacolada.interfaces.providers.PointerProvider;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.skills.PSpecialSkill;

@VisibleCard
public class Joker extends PCLCard {
    public static final PCLCardData DATA = register(Joker.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Ranged)
            .setDamage(1, array(0, 2, 0))
            .setHp(5, array(2, 1, 0))
            .setTags(PCLCardTag.Ethereal.make(1, array(0, 1, 1)))
            .setAffinities(PCLAffinity.Star)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Joker() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.DARKNESS);
        addUseMove(PCond.cooldown(0), getSpecialMove(0, this::specialMove, 2).setUpgrade(0, 0, 1));
    }

    public void specialMove(PSpecialSkill move, PCLUseInfo info, PCLActions order) {
        order.selectFromPile(name, move.amount, player.discardPile)
                .setFilter(c -> c instanceof PointerProvider && c.type == PCLEnum.CardType.SUMMON && !(c instanceof Joker)) // Filter out other Jokers to avoid infinites
                .setOrigin(PCLCardSelection.Random)
                .addCallback(cards -> {
                    for (AbstractCard c : cards) {
                        PointerProvider provider = EUIUtils.safeCast(c, PointerProvider.class);
                        if (provider != null) {
                            PCLEffects.Queue.showCardBriefly(c.makeStatEquivalentCopy());
                            provider.doEffects(ef -> {
                                // Do not use powers bestowed onto summons
                                if (!(ef instanceof SummonOnlyMove)) {
                                    ef.use(info, order);
                                }
                            });
                        }
                    }
                });
    }
}

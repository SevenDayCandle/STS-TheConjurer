package pinacolada.cards.conjurer.series.honkai;


import pinacolada.actions.PCLActions;
import pinacolada.actions.powers.ElementReaction;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.dungeon.AffinityReactions;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.skills.PSpecialSkill;

@VisibleCard
public class BronyaZaychik extends PCLCard {
    public static final PCLCardData DATA = register(BronyaZaychik.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Ranged)
            .setDamage(3, 2)
            .setHp(5, 1)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.honkai);

    public BronyaZaychik() {
        super(DATA);
    }

    public void action(PSpecialSkill move, PCLUseInfo info, PCLActions order) {
        AffinityReactions reactions = info.getAux(ConjurerReactionMeter.meter, AffinityReactions.class);
        if (reactions != null && !reactions.isEmpty()) {
            order.add(new ElementReaction(reactions, info.card, info));
        }
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.IRON);
        addUseMove(CCond.linkFront(PCLAffinity.Red, PCLAffinity.Orange), getSpecialMove(0, this::action));
    }
}

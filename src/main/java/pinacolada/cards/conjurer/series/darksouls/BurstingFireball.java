package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.EffekseerEFK;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.conjurer.modifiers.PMod_PerLevel;

@VisibleCard
public class BurstingFireball extends PCLCard {
    public static final PCLCardData DATA = register(BurstingFireball.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.UNCOMMON, PCLAttackType.Ranged, PCLCardTarget.AllEnemy)
            .setDamage(7, 4)
            .setAffinities(1, PCLAffinity.Red)
            .setTags(PCLCardTag.Exhaust)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public BurstingFireball() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.FIRE11);
        addUseMove(PMod.perCreature(PCLCardTarget.AllEnemy, 1), PMove.create(1, Fireball.DATA.ID));
    }
}

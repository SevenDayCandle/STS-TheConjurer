package pinacolada.cards.conjurer.series.monsterhunter;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardSelection;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Alatreon extends PCLCard {
    public static final PCLCardData DATA = register(Alatreon.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.UNCOMMON, PCLAttackType.Normal, PCLCardTarget.RandomEnemy)
            .setDamage(9, 1, 0)
            .setHp(20, 2)
            .setAffinities(PCLAffinity.Star)
            .setLoadout(ConjurerPlayerData.monsterHunter, true);

    public Alatreon() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.LIGHTNING);
        addGainPower(PTrigger.interactable(
                PCond.payEnergy(1),
                PMove.modifyAffinity(1, 2).edit(f -> f.setOrigin(PCLCardSelection.Random)))
        );
    }
}

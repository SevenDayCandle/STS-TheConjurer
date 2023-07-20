package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class Venti extends PCLCard {
    public static final PCLCardData DATA = register(Venti.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Ranged, PCLCardTarget.AllEnemy)
            .setDamage(2,  array(0, 0), 2, array(0, 1))
            .setTags(PCLCardTag.Ethereal.make(1, array(0, 1)))
            .setHp(4, 1)
            .setAffinities(PCLAffinity.Green.make(2), PCLAffinity.Yellow.make(1))
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Venti() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.WIND);
        addUseMove(PCond.cooldown(1), PMod.cyclePer(2).edit(f -> f.setType(CardType.ATTACK)), PMove.applyToEnemies(2, PCLElementHelper.Ventus).setUpgrade(0, 1));
    }
}

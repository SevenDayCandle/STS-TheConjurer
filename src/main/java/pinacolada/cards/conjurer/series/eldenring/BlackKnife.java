package pinacolada.cards.conjurer.series.eldenring;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
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
import pinacolada.skills.PCond;
import pinacolada.skills.skills.PLimit;
import pinacolada.skills.skills.base.moves.PMove_PermanentUpgrade;

@VisibleCard
public class BlackKnife extends PCLCard {
    public static final PCLCardData DATA = register(BlackKnife.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.SPECIAL, PCLAttackType.Piercing, PCLCardTarget.Single)
            .setDamage(15, 3)
            .setAffinities(PCLAffinity.Purple)
            .setTags(PCLCardTag.Exhaust)
            .setUnique(true, -1)
            .setLoadout(ConjurerPlayerData.eldenRing, true);

    public BlackKnife() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(AbstractGameAction.AttackEffect.NONE).setDamageEffect(EffekseerEFK.SWORD16);
        addUseMove(
                PLimit.limited(), PCond.fatal(), new PMove_PermanentUpgrade(1)
        );
    }
}
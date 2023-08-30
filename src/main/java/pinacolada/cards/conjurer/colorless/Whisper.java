package pinacolada.cards.conjurer.colorless;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Whisper extends PCLCard {
    public static final PCLCardData DATA = register(Whisper.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Immaterial)
            .setDamage(2, 2)
            .setHp(1, 0)
            .setAffinities(PCLAffinity.Purple)
            .setTags(PCLCardTag.Ethereal)
            .setLoadout(ConjurerPlayerData.ragnarok, true);

    public Whisper() {
        super(DATA);
    }

    @Override
    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.GHOST);
        addUseMove(PCond.onDeath(), PMove.applyToTeam(1, PCLPowerHelper.Intangible));
    }
}
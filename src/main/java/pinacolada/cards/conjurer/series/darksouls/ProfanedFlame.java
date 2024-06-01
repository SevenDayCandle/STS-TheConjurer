package pinacolada.cards.conjurer.series.darksouls;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.EffekseerEFK;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrait;
import pinacolada.skills.conjurer.conditions.PCond_React;
import pinacolada.skills.skills.base.conditions.PCond_HaveLastPlayed;

@VisibleCard
public class ProfanedFlame extends PCLCard {
    public static final PCLCardData DATA = register(ProfanedFlame.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.UNCOMMON, PCLAttackType.Immaterial)
            .setDamage(8, 2)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public ProfanedFlame() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.FIRE09);
        addUseMove(new PCond_HaveLastPlayed(1).edit(f -> f.setAffinity(PCLAffinity.Red)), PMove.applyToSingle(6, BlastedPower.DATA).setUpgrade(2));
    }
}

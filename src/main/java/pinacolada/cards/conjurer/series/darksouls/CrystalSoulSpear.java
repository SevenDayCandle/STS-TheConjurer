package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.EffekseerEFK;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.conjurer.conditions.PCond_React;
import pinacolada.skills.conjurer.modifiers.PMod_PerLevel;

@VisibleCard
public class CrystalSoulSpear extends PCLCard {
    public static final PCLCardData DATA = register(CrystalSoulSpear.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON, PCLAttackType.Piercing)
            .setDamage(15, 4)
            .setAffinities(2, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public CrystalSoulSpear() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.SPEAR02);
        addUseMove(new PCond_React(PCLAffinity.Red), PMove.applyToSingle(4, AquaPower.DATA));
    }
}

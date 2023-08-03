package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.EffekseerEFK;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

@VisibleCard
public class CrystalSoulSpear extends PCLCard {
    public static final PCLCardData DATA = register(CrystalSoulSpear.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON, PCLAttackType.Piercing)
            .setDamage(18, 3)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public CrystalSoulSpear() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.SPEAR02);
        addUseMove(PCond.checkLevel(3, PCLAffinity.Blue), PMultiSkill.join(PMove.selfExhaust(), PMod.perCard(1, PCLCardGroupHelper.DiscardPile).setChain(PMove.applyToSingle(2, PCLElementHelper.Frostbite))));
    }
}

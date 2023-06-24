package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Genbu extends PCLCard {
    public static final PCLCardData DATA = register(Genbu.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.UNCOMMON, PCLAttackType.Immaterial)
            .setDamage(1, 1, 2)
            .setHp(9, 2)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Genbu() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.CLAW);
        addGainPower(PTrigger.interactable(CCond.payMatter(9),
                PMultiSkill.choose(
                        PMove.applyToEnemies(6, PCLElementHelper.Aqua),
                        PMove.applyToEnemies(6, PCLElementHelper.Petra)
                )
        ));
    }
}

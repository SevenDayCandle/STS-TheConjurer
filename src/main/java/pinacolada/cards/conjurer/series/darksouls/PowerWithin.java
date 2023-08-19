package pinacolada.cards.conjurer.series.darksouls;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class PowerWithin extends PCLCard {
    public static final PCLCardData DATA = register(PowerWithin.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.RARE, PCLCardTarget.Team)
            .setAffinities(PCLAffinity.Red)
            .setTags(PCLCardTag.Exhaust)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public PowerWithin() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMod.bonusPerCreature(PCLCardTarget.AllAlly, 2), PMove.gain(1, PCLPowerHelper.Strength).setVFX(ConjurerEFK.MGC_PowerUp).setUpgrade(1));
        addUseMove(PMove.dealDamage(10, AbstractGameAction.AttackEffect.SMASH, PCLCardTarget.AllAlly));
    }
}

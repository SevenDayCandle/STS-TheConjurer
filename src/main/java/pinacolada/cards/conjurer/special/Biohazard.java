package pinacolada.cards.conjurer.special;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.powers.conjurer.VentusPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

@VisibleCard
public class Biohazard extends PCLCard {
    public static final PCLCardData DATA = register(Biohazard.class, ConjurerResources.conjurer)
            .setStatus(0, CardRarity.SPECIAL, PCLCardTarget.All)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange);

    public Biohazard() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PCond.onExhaust(), PMultiSkill.join(PMove.dealDamage(2, AbstractGameAction.AttackEffect.POISON, PCLCardTarget.All).setUpgrade(2), PMove.applyToEveryone(1, VentusPower.DATA).setUpgrade(1)));
    }
}